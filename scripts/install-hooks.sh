#!/bin/bash
# ============================================================================
# Install Git Hooks for Java Starter Kit
# Configures Git to use hooks from the version-controlled .githooks/ directory.
# Safe to run repeatedly — idempotent and fast when already configured.
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

# Check .githooks directory exists
if [[ -d "${HOOKS_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} Hooks source directory: ${HOOKS_SOURCE}"
else
    echo -e "  ${RED}✗${NC} Hooks source directory not found: ${HOOKS_SOURCE}"
    echo -e "  ${YELLOW}  Expected at project root level${NC}"
    PREREQ_PASS=false
fi

# Check .githooks has actual hook files
HOOK_FILES=$(find "${HOOKS_SOURCE}" -maxdepth 1 -type f 2>/dev/null | wc -l | tr -d ' ')
if [[ "${HOOK_FILES}" -gt 0 ]]; then
    echo -e "  ${GREEN}✓${NC} Found ${HOOK_FILES} hook file(s) in source"
else
    echo -e "  ${RED}✗${NC} No hook files found in ${HOOKS_SOURCE}"
    PREREQ_PASS=false
fi

# Check for required commands
REQUIRED_CMDS=("git")
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

echo -e "\n  ${GREEN}${BOLD}All prerequisites met! Proceeding...${NC}\n"

# ============================================================================
# Phase 2: Configure Git hooksPath
# ============================================================================
echo -e "${YELLOW}${BOLD}═══ Phase 2: Git Configuration ═══${NC}\n"

CURRENT_HOOKS_PATH=$(git config --get core.hooksPath 2>/dev/null || echo "")

if [[ "${CURRENT_HOOKS_PATH}" == "${HOOKS_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} core.hooksPath already set to: ${HOOKS_SOURCE}"
    echo -e "  ${DIM}    No changes needed — hooks are already active.${NC}"
    INSTALL_STATUS="already_configured"
elif [[ -n "${CURRENT_HOOKS_PATH}" ]]; then
    echo -e "  ${YELLOW}⚠${NC} core.hooksPath is currently set to: ${CURRENT_HOOKS_PATH}"
    git config core.hooksPath "${HOOKS_SOURCE}"
    echo -e "  ${GREEN}✓${NC} Updated core.hooksPath → ${HOOKS_SOURCE}"
    INSTALL_STATUS="updated"
else
    git config core.hooksPath "${HOOKS_SOURCE}"
    echo -e "  ${GREEN}✓${NC} Set core.hooksPath → ${HOOKS_SOURCE}"
    INSTALL_STATUS="installed"
fi

# Verify configuration
VERIFIED_PATH=$(git config --get core.hooksPath 2>/dev/null || echo "")
if [[ "${VERIFIED_PATH}" == "${HOOKS_SOURCE}" ]]; then
    echo -e "  ${GREEN}✓${NC} Git configuration verified"
else
    echo -e "  ${RED}✗${NC} Git configuration mismatch"
    echo -e "  ${YELLOW}  Expected: ${HOOKS_SOURCE}${NC}"
    echo -e "  ${YELLOW}  Got:      ${VERIFIED_PATH}${NC}"
    exit 1
fi

# ============================================================================
# Phase 3: Quick Smoke Test
# ============================================================================
echo -e "\n${YELLOW}${BOLD}═══ Phase 3: Smoke Tests ═══${NC}\n"

ALL_PASSED=true
for hook_file in "${HOOKS_SOURCE}"/*; do
    [[ -f "${hook_file}" ]] || continue
    hook_name=$(basename "${hook_file}")

    # Skip lib directory and non-script files
    [[ "${hook_name}" == "lib" ]] && continue
    [[ "${hook_name}" != *-* ]] && continue

    # Check it's executable
    if [[ -x "${hook_file}" ]]; then
        echo -e "  ${GREEN}✓${NC} ${hook_name} is executable"
    else
        echo -e "  ${YELLOW}⚠${NC} ${hook_name} is NOT executable — fixing..."
        chmod +x "${hook_file}"
        if [[ -x "${hook_file}" ]]; then
            echo -e "  ${GREEN}✓${NC} ${hook_name} now executable"
        else
            echo -e "  ${RED}✗${NC} ${hook_name} could not be made executable"
            ALL_PASSED=false
        fi
    fi

    # Validate bash syntax
    if bash -n "${hook_file}" 2>/dev/null; then
        echo -e "  ${GREEN}✓${NC} ${hook_name} syntax check passed"
    else
        echo -e "  ${RED}✗${NC} ${hook_name} has syntax errors!"
        bash -n "${hook_file}" 2>&1 | sed 's/^/    /'
        ALL_PASSED=false
    fi

    # Check shared library reference
    if grep -q 'source.*lib/shared.sh' "${hook_file}" 2>/dev/null; then
        if [[ -f "${HOOKS_SOURCE}/lib/shared.sh" ]]; then
            echo -e "  ${GREEN}✓${NC} ${hook_name} references shared library (found)"
        else
            echo -e "  ${RED}✗${NC} ${hook_name} references shared library but lib/shared.sh is missing!"
            ALL_PASSED=false
        fi
    fi
done

# ============================================================================
# Summary
# ============================================================================
echo ""
echo -e "${CYAN}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
if [[ "${ALL_PASSED}" == "true" ]]; then
    echo -e "${CYAN}${BOLD}║     ✅  HOOKS ACTIVE — Ready for commits & pushes        ║${NC}"
else
    echo -e "${YELLOW}${BOLD}║     ⚠  INSTALLED WITH WARNINGS                          ║${NC}"
fi
echo -e "${CYAN}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
echo ""

echo -e "  ${CYAN}Hooks directory:${NC} ${HOOKS_SOURCE}"
echo -e "  ${CYAN}Git config:${NC}     core.hooksPath = $(git config --get core.hooksPath)"
echo ""

echo -e "  ${DIM}Active hooks:${NC}"
for hook_file in "${HOOKS_SOURCE}"/*; do
    [[ -f "${hook_file}" ]] || continue
    hook_name=$(basename "${hook_file}")
    [[ "${hook_name}" == "lib" ]] && continue
    [[ "${hook_name}" != *-* ]] && continue
    echo -e "    ${GREEN}✓${NC} ${hook_name}"
done
echo ""

case "${INSTALL_STATUS}" in
    "already_configured")
        echo -e "  ${GREEN}${BOLD}Hooks were already active. No changes needed.${NC}"
        ;;
    "updated")
        echo -e "  ${GREEN}${BOLD}Hooks configuration updated successfully.${NC}"
        ;;
    "installed")
        echo -e "  ${GREEN}${BOLD}Hooks installed successfully!${NC}"
        ;;
esac
echo ""
echo -e "  ${DIM}To bypass hooks temporarily: git commit --no-verify${NC}"
echo -e "  ${DIM}To reinstall:               bash scripts/install-hooks.sh${NC}"
echo -e "  ${DIM}To check status:            git config --get core.hooksPath${NC}"
echo ""