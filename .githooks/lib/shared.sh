#!/bin/bash
# ============================================================================
# Shared Library for Java Starter Kit Git Hooks
# Provides common utilities: dependency checking, logging, caching, parallel exec
# ============================================================================

# Prevent double-include
if [[ -n "${_SHARED_LIB_LOADED:-}" ]]; then
    return 0
fi
_SHARED_LIB_LOADED=1

# ============================================================================
# Configuration
# ============================================================================
PROJECT_ROOT="$(git rev-parse --show-toplevel 2>/dev/null || echo "$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)")"
HOOKS_DIR="${PROJECT_ROOT}/.githooks"
CACHE_DIR="${PROJECT_ROOT}/.git/.hooks-cache"
REPORT_DIR="${PROJECT_ROOT}/build/reports/pre-commit"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
CACHE_TTL=300  # 5 minutes cache TTL

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
MAGENTA='\033[0;35m'
NC='\033[0m'
BOLD='\033[1m'
DIM='\033[2m'

# ============================================================================
# State
# ============================================================================
PASS=true
STAGED_FILES=""
CURRENT_BRANCH=""

# ============================================================================
# Initialization
# ============================================================================
init_hook() {
    cd "${PROJECT_ROOT}" 2>/dev/null || {
        echo -e "  ${RED}✗${NC} Failed to change to project root: ${PROJECT_ROOT}"
        return 1
    }
    mkdir -p "${CACHE_DIR}" "${REPORT_DIR}"
    CURRENT_BRANCH=$(git symbolic-ref HEAD 2>/dev/null | sed 's|refs/heads/||')
    STAGED_FILES=$(git diff --cached --name-only --diff-filter=ACMR 2>/dev/null || echo "")
}

# ============================================================================
# Terminal Output
# ============================================================================
print_banner() {
    local title="$1"
    local color="${2:-${CYAN}}"
    echo -e "\n${color}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${color}${BOLD}║     ${title}${NC}"
    echo -e "${color}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
    echo ""
}

print_section() {
    local section_num="$1"
    local total="$2"
    local icon="$3"
    local title="$4"
    echo -e "\n${YELLOW}${BOLD}═══ ${section_num}/${total} ${icon}  ${title} ═══${NC}\n"
}

print_pass() {
    echo -e "  ${GREEN}✓${NC} $1"
}

print_warn() {
    echo -e "  ${YELLOW}⚠${NC} $1"
}

print_fail() {
    echo -e "  ${RED}✗${NC} $1"
    PASS=false
}

print_info() {
    echo -e "  ${CYAN}ℹ${NC} $1"
}

print_detail() {
    echo -e "  ${DIM}$1${NC}"
}

print_separator() {
    echo -e "  ${DIM}──────────────────────────────────────────${NC}"
}

print_timing() {
    local elapsed=$1
    local label="$2"
    if (( elapsed >= 10 )); then
        echo -e "  ${DIM}⏱ ${label}: ${elapsed}s${NC}"
    fi
}

# ============================================================================
# Dependency Checking - "Checks if not present" feature
# ============================================================================

# Describes required tools with install guidance
# Uses functions instead of associative arrays for Bash 3.x compatibility (macOS)
_get_tool_info() {
    local tool_key="$1"
    case "${tool_key}" in
        git)    echo "git|git|https://git-scm.com/downloads|Required for version control operations" ;;
        bash)   echo "bash|bash|https://www.gnu.org/software/bash/|Required for hook scripts" ;;
        java)   echo "java|JDK 25+|https://adoptium.net/temurin/releases/|Required for Gradle builds" ;;
        gradle) echo "./gradlew|Gradle 8.x|https://gradle.org/install/|Build tool - use wrapper (./gradlew)" ;;
        grep)   echo "grep|grep|built-in|Required for pattern matching" ;;
        sed)    echo "sed|sed|built-in|Required for text processing" ;;
        awk)    echo "awk|awk|built-in|Required for text processing" ;;
        stat)   echo "stat|stat|built-in|Required for file size checks" ;;
        xargs)  echo "xargs|xargs|built-in|Required for file processing" ;;
        find)   echo "find|find|built-in|Required for file search" ;;
        sort)   echo "sort|sort|built-in|Required for file sorting" ;;
        *)      echo "" ;;
    esac
}

# Check if a specific tool/command exists
# Returns 0 if found, 1 if not found
check_tool() {
    local tool_name="$1"
    local command="${2:-${tool_name}}"
    
    if command -v "${command}" &>/dev/null; then
        return 0
    fi
    return 1
}

# Check required tools and report which ones are missing with install guidance
check_required_tools() {
    local required_tools=("$@")
    local missing=0
    local missing_details=""

    for tool_key in "${required_tools[@]}"; do
        local tool_info
        tool_info=$(_get_tool_info "${tool_key}")
        if [[ -z "${tool_info}" ]]; then
            # Tool not in registry, just check by name
            if ! command -v "${tool_key}" &>/dev/null; then
                missing=$((missing + 1))
                missing_details+="    ${RED}✗${NC} ${tool_key} - not found\n"
            fi
            continue
        fi

        local cmd name url desc
        IFS='|' read -r cmd name url desc <<< "${tool_info}"
        local check_cmd="${cmd:-${tool_key}}"

        if ! command -v "${check_cmd}" &>/dev/null; then
            missing=$((missing + 1))
            missing_details+="    ${RED}✗${NC} ${name} (${cmd}) - ${desc}\n"
            missing_details+="      ${CYAN}Install:${NC} ${url}\n"
        fi
    done

    if (( missing > 0 )); then
        echo -e "\n  ${RED}${BOLD}Missing Required Tools (${missing}):${NC}"
        echo -e "${missing_details}"
        return 1
    fi

    return 0
}

# Check for optional tools (warn, don't fail)
check_optional_tools() {
    local optional_tools=("$@")
    local missing=0

    for tool_key in "${optional_tools[@]}"; do
        local tool_info
        tool_info=$(_get_tool_info "${tool_key}")
        if [[ -z "${tool_info}" ]]; then
            continue
        fi

        local cmd name url desc
        IFS='|' read -r cmd name url desc <<< "${tool_info}"
        local check_cmd="${cmd:-${tool_key}}"

        if ! command -v "${check_cmd}" &>/dev/null; then
            missing=$((missing + 1))
            print_info "${name} not found - ${desc}"
            print_detail "       Install: ${url}"
        fi
    done

    if (( missing > 0 )); then
        echo ""
    fi

    return 0
}

# Check Gradle wrapper exists and is executable
check_gradle_wrapper() {
    if [[ ! -x "${PROJECT_ROOT}/gradlew" ]]; then
        print_fail "Gradle wrapper (gradlew) not found or not executable"
        print_info "Run: chmod +x gradlew"
        return 1
    fi
    return 0
}

# Check Java is available and meets minimum version
check_java_version() {
    if ! command -v java &>/dev/null; then
        print_fail "Java not found. Install JDK 25+ from https://adoptium.net/"
        return 1
    fi

    local java_version
    java_version=$(java -version 2>&1 | head -1 | sed 's/.*version "\([0-9]*\).*/\1/')
    
    if [[ -z "${java_version}" ]]; then
        print_warn "Could not determine Java version"
        return 0
    fi

    if (( java_version < 21 )); then
        print_warn "Java version ${java_version} detected - recommend JDK 25+"
        print_detail "       Current: $(java -version 2>&1 | head -1)"
    else
        print_detail "Java version: $(java -version 2>&1 | head -1)"
    fi
    return 0
}

# Comprehensive dependency check - runs all checks and returns status
check_all_dependencies() {
    local check_type="${1:-all}"  # all, quick, full
    local all_ok=true

    print_info "Checking system dependencies..."

    # Essential tools that must always be present
    local essentials=("git" "bash" "grep" "sed" "awk" "xargs" "find" "sort")
    if ! check_required_tools "${essentials[@]}"; then
        all_ok=false
    fi

    # OS-specific stat check
    if ! command -v stat &>/dev/null; then
        print_warn "stat not found - large file detection disabled"
    fi

    # Gradle wrapper
    if ! check_gradle_wrapper; then
        all_ok=false
    fi

    # Java (required for Gradle)
    if ! check_java_version; then
        all_ok=false
    fi

    # Optional tools for enhanced checks
    if [[ "${check_type}" == "full" ]]; then
        check_optional_tools "java"
    fi

    if [[ "${all_ok}" == "false" ]]; then
        echo -e "\n  ${YELLOW}Fix missing dependencies and re-run the hook.${NC}"
        echo -e "  ${YELLOW}To bypass: git commit --no-verify${NC}"
        return 1
    fi

    print_pass "All required dependencies found"
    return 0
}

# ============================================================================
# Caching
# ============================================================================

# Check if a cached result is still valid
cache_get() {
    local key="$1"
    local cache_file="${CACHE_DIR}/${key}"
    
    if [[ -f "${cache_file}" ]]; then
        local cache_age
        cache_age=$(( $(date +%s) - $(stat -f%m "${cache_file}" 2>/dev/null || stat -c%Y "${cache_file}" 2>/dev/null) ))
        if (( cache_age < CACHE_TTL )); then
            cat "${cache_file}"
            return 0
        fi
    fi
    return 1
}

# Store a value in the cache
cache_set() {
    local key="$1"
    local value="$2"
    echo "${value}" > "${CACHE_DIR}/${key}" 2>/dev/null || true
}

# Invalidate cache for a specific key or all
cache_invalidate() {
    local key="${1:-}"
    if [[ -n "${key}" ]]; then
        rm -f "${CACHE_DIR}/${key}" 2>/dev/null || true
    else
        rm -f "${CACHE_DIR}"/* 2>/dev/null || true
    fi
}

# Generate a cache key from a list of files
cache_key_from_files() {
    local prefix="$1"
    shift
    echo "${prefix}-$(echo "$*" | md5sum 2>/dev/null | cut -d' ' -f1 || echo "$*" | md5 -r 2>/dev/null | cut -d' ' -f1 || echo "${RANDOM}")"
}

# ============================================================================
# Parallel Execution
# ============================================================================

# Run tasks in parallel and wait for all to complete
# Usage: run_parallel "label" "task_name" "command" ["task_name2" "command2" ...]
run_parallel() {
    local label="$1"
    shift
    local pids=()
    local names=()
    local results=()
    local i=0

    print_info "Running ${label} (parallel)..."

    while (( $# >= 2 )); do
        local task_name="$1"
        local task_cmd="$2"
        shift 2

        names+=("${task_name}")
        # Run in background, write result to temp file
        local tmp_file="${CACHE_DIR}/parallel_${i}_${TIMESTAMP}"
        eval "${task_cmd}" > "${tmp_file}" 2>&1 &
        pids+=($!)
        results+=("${tmp_file}")
        ((i++))
    done

    # Wait for all and collect results
    local all_ok=true
    for (( idx=0; idx<${#pids[@]}; idx++ )); do
        wait "${pids[$idx]}" 2>/dev/null
        local exit_code=$?
        local output=""
        [[ -f "${results[$idx]}" ]] && output=$(cat "${results[$idx]}") && rm -f "${results[$idx]}"

        if (( exit_code == 0 )); then
            print_pass "${names[$idx]}"
            [[ -n "${output}" ]] && print_detail "${output}"
        else
            print_fail "${names[$idx]}"
            [[ -n "${output}" ]] && echo -e "  ${RED}${output}${NC}"
            all_ok=false
        fi
    done

    [[ "${all_ok}" == "true" ]] && return 0 || return 1
}

# ============================================================================
# Staged File Analysis
# ============================================================================

# Get list of staged files
get_staged_files() {
    echo "${STAGED_FILES}"
}

# Get count of staged files
get_staged_count() {
    if [[ -z "${STAGED_FILES}" ]]; then
        echo "0"
    else
        echo "${STAGED_FILES}" | wc -l | tr -d ' '
    fi
}

# Check if any staged files match a pattern
staged_files_match() {
    local pattern="$1"
    echo "${STAGED_FILES}" | grep -qE "${pattern}" 2>/dev/null
    return $?
}

# Get staged files by extension
get_staged_by_extension() {
    local ext="$1"
    echo "${STAGED_FILES}" | grep -E "\.${ext}$" 2>/dev/null || true
}

# Check for Java files in staged files
has_java_files() {
    staged_files_match "\.java$"
}

# Check for Kotlin files in staged files
has_kotlin_files() {
    staged_files_match "\.kt$"
}

# Check for Gradle files in staged files
has_gradle_files() {
    staged_files_match "\.gradle(\.kts)?$|settings\.gradle"
}

# Check for YAML/JSON files in staged files
has_config_files() {
    staged_files_match "\.(yml|yaml|json|xml|properties)$"
}

# Check for frontend files in staged files
has_frontend_files() {
    staged_files_match "\.(js|ts|jsx|tsx|css|scss|html)$"
}

# Check for Docker/K8s files in staged files
has_infra_files() {
    staged_files_match "Dockerfile|docker-compose|\.yaml$|\.k8s\.|terraform|\.tf$"
}

# ============================================================================
# Content Scanning
# ============================================================================

# Scan staged files for potential secrets/credentials
scan_for_secrets() {
    local files="$1"
    local found_any=false
    local temp_file="${CACHE_DIR}/secrets_scan_${TIMESTAMP}"

    # Patterns that look like secrets
    local patterns=(
        'password\s*[=:]\s*['"'"'"]?\S+'
        'secret\s*[=:]\s*['"'"'"]?\S+'
        'api[_-]?key\s*[=:]\s*['"'"'"]?\S+'
        'api[_-]?secret\s*[=:]\s*['"'"'"]?\S+'
        'access[_-]?key\s*[=:]\s*['"'"'"]?\S+'
        'private[_-]?key\s*[=:]\s*['"'"'"]?\S+'
        'token\s*[=:]\s*['"'"'"]?\S+'
        'AKIA[0-9A-Z]{16}'  # AWS Access Key
        '-----BEGIN (RSA |EC )?PRIVATE KEY-----'
        'ghp_[A-Za-z0-9]{36}'  # GitHub PAT
        'gho_[A-Za-z0-9]{36}'  # GitHub OAuth
        'github_pat_[A-Za-z0-9_]{82}'  # GitHub Fine-grained PAT
        'sk-[A-Za-z0-9]{32,}'  # OpenAI API key
        'xox[baprs]-[A-Za-z0-9-]{24,}'  # Slack token
    )

    echo "${files}" | while IFS= read -r file; do
        [[ -z "${file}" ]] && continue
        [[ ! -f "${file}" ]] && continue

        # Skip binary files
        if file "${file}" | grep -qi "binary"; then
            continue
        fi

        for pattern in "${patterns[@]}"; do
            if grep -lqE "${pattern}" "${file}" 2>/dev/null; then
                echo "${file}"
                found_any=true
                break
            fi
        done
    done

    [[ -f "${temp_file}" ]] && rm -f "${temp_file}"
}

# Scan for TODO/FIXME/HACK markers
scan_for_todos() {
    local files="$1"
    local todos_found=0
    local fixes_found=0
    local hacks_found=0

    echo "${files}" | while IFS= read -r file; do
        [[ -z "${file}" ]] && continue
        [[ ! -f "${file}" ]] && continue

        if file "${file}" | grep -qi "binary"; then
            continue
        fi

        local todos
        todos=$(grep -c "TODO\|FIXME\|HACK\|XXX" "${file}" 2>/dev/null || echo "0")
        todos_found=$((todos_found + todos))
    done

    echo "${todos_found}"
}

# ============================================================================
# Git utility
# ============================================================================

# Get the commit message file path
get_commit_msg_file() {
    local msg_file="${1:-}"
    if [[ -z "${msg_file}" ]]; then
        msg_file=".git/COMMIT_EDITMSG"
    fi
    echo "${msg_file}"
}

# Get current commit message
get_commit_message() {
    local msg_file
    msg_file=$(get_commit_msg_file "$1")
    if [[ -f "${msg_file}" ]]; then
        head -n 1 "${msg_file}" 2>/dev/null || echo ""
    else
        echo ""
    fi
}

# ============================================================================
# Final summary
# ============================================================================

show_summary() {
    local hook_name="$1"
    local elapsed="${2:-0}"
    local hook_lower
    # Lowercase conversion for Bash 3.x compatibility (macOS)
    hook_lower=$(echo "${hook_name}" | tr '[:upper:]' '[:lower:]')

    echo ""
    if [[ "${PASS}" == "true" ]]; then
        echo -e "${GREEN}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
        echo -e "${GREEN}${BOLD}║     ✅  ALL CHECKS PASSED - ${hook_name} ALLOWED            ║${NC}"
        echo -e "${GREEN}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
        echo ""
        echo -e "  ${GREEN}${hook_name} is ready to proceed!${NC}"
        if (( elapsed > 0 )); then
            echo -e "  ${DIM}Completed in ${elapsed}s${NC}"
        fi
        echo ""
    else
        echo -e "${RED}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
        echo -e "${RED}${BOLD}║     ❌  CHECKS FAILED - ${hook_name} BLOCKED                ║${NC}"
        echo -e "${RED}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
        echo ""
        echo -e "  ${RED}Please fix the issues above and try again.${NC}"
        echo ""
        echo -e "  ${YELLOW}To bypass checks temporarily (not recommended):${NC}"
        echo -e "    git ${hook_lower} --no-verify"
        echo ""
        exit 1
    fi
}

# ============================================================================
# Check if hook is running properly (self-validation)
# ============================================================================

self_validate() {
    local hook_name="$1"
    local errors=0

    # Check we can access project root
    if [[ ! -d "${PROJECT_ROOT}" ]]; then
        echo "  ${RED}✗${NC} Cannot access project root: ${PROJECT_ROOT}"
        errors=$((errors + 1))
    fi

    # Check git repository
    if ! git rev-parse --git-dir &>/dev/null; then
        echo "  ${RED}✗${NC} Not in a git repository"
        errors=$((errors + 1))
    fi

    # Check this script is sourced correctly
    if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
        echo "  ${YELLOW}⚠${NC} This script should be sourced, not executed directly"
    fi

    # Check for circular dependencies
    if [[ -n "${_HOOK_RUNNING:-}" ]]; then
        echo "  ${RED}✗${NC} Circular hook execution detected (${_HOOK_RUNNING})"
        exit 1
    fi
    export _HOOK_RUNNING="${hook_name}"

    if (( errors > 0 )); then
        echo "  ${RED}Hook self-validation failed${NC}"
        exit 1
    fi

    return 0
}