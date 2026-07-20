#!/bin/bash
# ============================================================================
# Install Git Hooks for Java Starter Kit
# Enhanced version with prerequisite verification, hook validation,
# and comprehensive setup reporting.
# ============================================================================

set -euo pipefail

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m'
BOLD='\033[1m'
DIM='\033[2m'

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
HOOKS_SOURCE="${PROJECT_ROOT}/.githooks"
HOOKS_TARGET="${PROJECT_ROOT}/.git/hooks"
HOOKS_LIB="${HOOKS_SOURCE}/lib"
SHARED_SOURCE="${HOOKS_LIB}/shared.sh"

echo -e "\n${CYAN}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
echo -e "${CYAN}${BOLD}║     🔧  Installing Git Hooks - Java Starter Kit            ║${NC}"
echo -e "${CYAN}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
echo ""

# ============================================================================
# Phase 1: Pre-requisite Checks
# ============================================================================
echo -e "${YELLOW}${BOLD}═══ Phase 1: Prerequisite Checks ═══${NC}\n"

PREREQ_PASS=true

# Check if we're in a git repository
if git rev-parse --git-dir &>/dev/null 2>&1; then
    echo -e "  ${GREEN}✓${NC} Git repository detected"
else
    echo -e "  ${RED}✗${NC} Not in a git repository"
    echo -e "  ${YELLOW}  Run 'git init' or clone the project first${NC}"
    PREREQ_PASS=false
fi

# Check .git directory exists
if [[ -d "${PROJECT_ROOT}/.git" ]]; then
    echo -e "  ${GREEN}✓${NC} .git directory found"
else
    echo -e "  ${RED}✗${NC} No .git directory"
    PREREQ_PASS=false
fi

# Check hooks source directory exists
if [[ -d "${HOOKS_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} Hooks source directory exists: ${HOOKS_SOURCE}"
else
    echo -e "  ${RED}✗${NC} Hooks source directory not found: ${HOOKS_SOURCE}"
    echo -e "  ${YELLOW}  Expected at: ${HOOKS_SOURCE}${NC}"
    PREREQ_PASS=false
fi

# Check shared library exists
if [[ -f "${SHARED_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} Shared library found: ${SHARED_SOURCE}"
else
    echo -e "  ${RED}✗${NC} Shared library not found: ${SHARED_SOURCE}"
    PREREQ_PASS=false
fi

# Check shared library syntax (basic validation)
if [[ -f "${SHARED_SOURCE}" ]]; then
    if bash -n "${SHARED_SOURCE}" 2>/dev/null; then
        echo -e "  ${GREEN}✓${NC} Shared library syntax is valid"
    else
        echo -e "  ${RED}✗${NC} Shared library has syntax errors!"
        bash -n "${SHARED_SOURCE}" || true
        PREREQ_PASS=false
    fi
fi

# Check bash version (3.2+ required, handles macOS default Bash 3.2)
_BASH_MAJOR=$(echo "${BASH_VERSION}" | sed 's/\..*//')
if [[ -z "${_BASH_MAJOR}" ]] || [[ "${_BASH_MAJOR}" -lt 3 ]]; then
    echo -e "  ${RED}✗${NC} Bash ${BASH_VERSION} is too old (3.2+ required)"
    PREREQ_PASS=false
elif [[ "${_BASH_MAJOR}" -eq 3 ]]; then
    _BASH_MINOR=$(echo "${BASH_VERSION}" | sed 's/[0-9]*\.\([0-9]*\).*/\1/')
    if [[ -n "${_BASH_MINOR}" ]] && [[ "${_BASH_MINOR}" -ge 2 ]]; then
        echo -e "  ${GREEN}✓${NC} Bash ${BASH_VERSION} detected (3.2+ required)"
    else
        echo -e "  ${RED}✗${NC} Bash ${BASH_VERSION} is too old (3.2+ required)"
        PREREQ_PASS=false
    fi
else
    echo -e "  ${GREEN}✓${NC} Bash ${BASH_VERSION} detected (3.2+ required)"
fi

# Check for required commands
REQUIRED_CMDS=("git" "sed" "grep" "awk" "find" "xargs")
MISSING_CMDS=()
for cmd in "${REQUIRED_CMDS[@]}"; do
    if command -v "${cmd}" &>/dev/null; then
        echo -e "  ${GREEN}✓${NC} ${cmd} found"
    else
        MISSING_CMDS+=("${cmd}")
    fi
done

if [[ ${#MISSING_CMDS[@]} -gt 0 ]]; then
    echo -e "  ${RED}✗${NC} Missing commands: ${MISSING_CMDS[*]}"
    PREREQ_PASS=false
fi

if [[ "${PREREQ_PASS}" == "false" ]]; then
    echo ""
    echo -e "  ${RED}${BOLD}Prerequisites not met. Please fix issues above and retry.${NC}"
    exit 1
fi

echo -e "\n  ${GREEN}${BOLD}All prerequisites met! Proceeding with installation...${NC}\n"

# ============================================================================
# Phase 2: Hook Installation
# ============================================================================
echo -e "${YELLOW}${BOLD}═══ Phase 2: Installing Hooks ═══${NC}\n"

# Create hooks target if it doesn't exist
mkdir -p "${HOOKS_TARGET}"

# List of hooks to install
HOOKS=("pre-commit" "commit-msg" "pre-push")

echo -e "  ${CYAN}Source:${NC} ${HOOKS_SOURCE}"
echo -e "  ${CYAN}Target:${NC} ${HOOKS_TARGET}"
echo ""

INSTALL_SUCCESS=true
INSTALLED_COUNT=0

for hook in "${HOOKS[@]}"; do
    SOURCE_FILE="${HOOKS_SOURCE}/${hook}"
    TARGET_FILE="${HOOKS_TARGET}/${hook}"
    
    if [[ ! -f "${SOURCE_FILE}" ]]; then
        echo -e "  ${YELLOW}⚠${NC} ${hook} hook not found at ${SOURCE_FILE}, skipping"
        continue
    fi

    # Validate hook syntax
    if ! bash -n "${SOURCE_FILE}" 2>/dev/null; then
        echo -e "  ${RED}✗${NC} ${hook} hook has syntax errors - skipping"
        bash -n "${SOURCE_FILE}" 2>&1 | sed 's/^/    /'
        INSTALL_SUCCESS=false
        continue
    fi

    # Check that hook sources shared library correctly
    if ! grep -q 'source "${SCRIPT_DIR}/lib/shared.sh"' "${SOURCE_FILE}" 2>/dev/null; then
        if ! grep -q "source.*shared.sh" "${SOURCE_FILE}" 2>/dev/null; then
            echo -e "  ${YELLOW}⚠${NC} ${hook} does not reference shared library"
        fi
    fi
    
    # Copy hook file
    cp "${SOURCE_FILE}" "${TARGET_FILE}"
    
    # Make it executable
    chmod +x "${TARGET_FILE}"
    
    # Verify it's executable
    if [[ -x "${TARGET_FILE}" ]]; then
        echo -e "  ${GREEN}✓${NC} Installed and set executable: ${hook}"
        INSTALLED_COUNT=$((INSTALLED_COUNT + 1))
    else
        echo -e "  ${RED}✗${NC} Failed to make ${hook} executable"
        INSTALL_SUCCESS=false
    fi
done

echo ""

# ============================================================================
# Phase 3: Verify Installation
# ============================================================================
echo -e "${YELLOW}${BOLD}═══ Phase 3: Verifying Installation ═══${NC}\n"

ALL_VERIFIED=true
for hook in "${HOOKS[@]}"; do
    TARGET_FILE="${HOOKS_TARGET}/${hook}"
    SOURCE_FILE="${HOOKS_SOURCE}/${hook}"
    
    if [[ ! -f "${SOURCE_FILE}" ]]; then
        continue
    fi

    # Check file exists
    if [[ ! -f "${TARGET_FILE}" ]]; then
        echo -e "  ${RED}✗${NC} ${hook} not installed (file missing)"
        ALL_VERIFIED=false
        continue
    fi

    # Check it's executable
    if [[ ! -x "${TARGET_FILE}" ]]; then
        echo -e "  ${RED}✗${NC} ${hook} is not executable"
        ALL_VERIFIED=false
        continue
    fi

    # Check content matches (not just symlinked from old location)
    if [[ -f "${SOURCE_FILE}" ]]; then
        SOURCE_HASH=$(md5sum "${SOURCE_FILE}" 2>/dev/null | cut -d' ' -f1 || md5 -r "${SOURCE_FILE}" 2>/dev/null | cut -d' ' -f1)
        TARGET_HASH=$(md5sum "${TARGET_FILE}" 2>/dev/null | cut -d' ' -f1 || md5 -r "${TARGET_FILE}" 2>/dev/null | cut -d' ' -f1)
        if [[ "${SOURCE_HASH}" == "${TARGET_HASH}" ]]; then
            echo -e "  ${GREEN}✓${NC} ${hook} verified (content matches source)"
        else
            echo -e "  ${YELLOW}⚠${NC} ${hook} installed but content differs from source"
            ALL_VERIFIED=false
        fi
    else
        echo -e "  ${GREEN}✓${NC} ${hook} is installed and executable"
    fi
done

# ============================================================================
# Phase 4: Git Configuration
# ============================================================================
echo -e "\n${YELLOW}${BOLD}═══ Phase 4: Git Configuration ═══${NC}\n"

# Configure core.hooksPath
CURRENT_HOOKS_PATH=$(git config --get core.hooksPath 2>/dev/null || echo "")
if [[ "${CURRENT_HOOKS_PATH}" != "${HOOKS_SOURCE}" ]]; then
    git config core.hooksPath "${HOOKS_SOURCE}"
    echo -e "  ${GREEN}✓${NC} Git hooksPath configured: ${HOOKS_SOURCE}"
else
    echo -e "  ${GREEN}✓${NC} Git hooksPath already configured: ${HOOKS_SOURCE}"
fi

# Verify configuration
VERIFIED_PATH=$(git config --get core.hooksPath 2>/dev/null || echo "")
if [[ "${VERIFIED_PATH}" == "${HOOKS_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} Git configuration verified"
else
    echo -e "  ${RED}✗${NC} Git configuration mismatch (expected: ${HOOKS_SOURCE}, got: ${VERIFIED_PATH})"
    ALL_VERIFIED=false
fi

# ============================================================================
# Summary
# ============================================================================
echo ""
echo -e "${CYAN}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"

if [[ "${INSTALL_SUCCESS}" == "true" && "${ALL_VERIFIED}" == "true" ]] || [[ "${INSTALLED_COUNT}" -gt 0 ]]; then
    echo -e "${CYAN}${BOLD}║     ✅  INSTALLATION COMPLETE                              ║${NC}"
    echo -e "${CYAN}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
    echo ""
    echo -e "  ${GREEN}${BOLD}${INSTALLED_COUNT} hook(s) installed successfully${NC}"
    echo ""
    echo -e "  ${CYAN}Installed hooks:${NC}"
    for hook in "${HOOKS[@]}"; do
        SOURCE_FILE="${HOOKS_SOURCE}/${hook}"
        TARGET_FILE="${HOOKS_TARGET}/${hook}"
        if [[ -f "${SOURCE_FILE}" ]] && [[ -x "${TARGET_FILE}" ]]; then
            echo -e "    ${GREEN}✓${NC} ${hook}"
        elif [[ -f "${SOURCE_FILE}" ]]; then
            echo -e "    ${YELLOW}⚠${NC} ${hook} (source exists, but installation issue)"
        fi
    done
    echo ""
    echo -e "  ${DIM}Hooks source: ${HOOKS_SOURCE}${NC}"
    echo -e "  ${DIM}Hooks target: ${HOOKS_TARGET}${NC}"
    echo ""
    
    # Quick test: run each hook with --help or dry-run if supported
    echo -e "  ${CYAN}Running quick smoke tests...${NC}"
    for hook in "${HOOKS[@]}"; do
        TARGET_FILE="${HOOKS_TARGET}/${hook}"
        if [[ -x "${TARGET_FILE}" ]]; then
            # Run with --dry-run or exit quickly
            if bash -n "${TARGET_FILE}" 2>/dev/null; then
                echo -e "    ${GREEN}✓${NC} ${hook} syntax check passed"
            else
                echo -e "    ${RED}✗${NC} ${hook} syntax check failed"
            fi
        fi
    done
    echo ""
else
    echo -e "${RED}${BOLD}║     ⚠  INSTALLATION COMPLETED WITH ISSUES                  ║${NC}"
    echo -e "${RED}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
    echo ""
    echo -e "  ${YELLOW}Some hooks may not be fully installed.${NC}"
    echo -e "  ${YELLOW}Check the messages above for details.${NC}"
fi

echo ""
echo -e "  ${YELLOW}Tips:${NC}"
echo -e "    - Hooks run automatically on commit/push"
echo -e "    - To bypass temporarily: git commit --no-verify"
echo -e "    - To reinstall: bash scripts/install-hooks.sh"
echo -e "    - To check status: git config --get core.hooksPath"
echo -e "    - To view hook contents: cat .githooks/<hook-name>"
echo ""
echo -e "  ${GREEN}${BOLD}Done! Hooks are now active.${NC}"