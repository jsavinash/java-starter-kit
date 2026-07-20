#!/bin/bash
# ============================================================================
# Install Git Hooks for Java Starter Kit
# ============================================================================

set -euo pipefail

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m'
BOLD='\033[1m'

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
HOOKS_SOURCE="${PROJECT_ROOT}/.githooks"
HOOKS_TARGET="${PROJECT_ROOT}/.git/hooks"

echo -e "\n${CYAN}${BOLD}╔══════════════════════════════════════════════════════════════╗${NC}"
echo -e "${CYAN}${BOLD}║     🔧  Installing Git Hooks                               ║${NC}"
echo -e "${CYAN}${BOLD}╚══════════════════════════════════════════════════════════════╝${NC}"
echo ""

# Check if .git directory exists
if [ ! -d "${PROJECT_ROOT}/.git" ]; then
    echo -e "  ${RED}✗${NC} No .git directory found. Are you in a git repository?"
    exit 1
fi

# Create hooks target if it doesn't exist
mkdir -p "${HOOKS_TARGET}"

# List of hooks to install
HOOKS=("pre-commit" "commit-msg" "pre-push")

echo -e "  ${CYAN}Source:${NC} ${HOOKS_SOURCE}"
echo -e "  ${CYAN}Target:${NC} ${HOOKS_TARGET}"
echo ""

for hook in "${HOOKS[@]}"; do
    SOURCE_FILE="${HOOKS_SOURCE}/${hook}"
    TARGET_FILE="${HOOKS_TARGET}/${hook}"
    
    if [ -f "${SOURCE_FILE}" ]; then
        # Copy hook file
        cp "${SOURCE_FILE}" "${TARGET_FILE}"
        
        # Make it executable
        chmod +x "${TARGET_FILE}"
        
        echo -e "  ${GREEN}✓${NC} Installed ${CYAN}${hook}${NC} hook"
    else
        echo -e "  ${YELLOW}⚠${NC} ${hook} hook not found at ${SOURCE_FILE}, skipping"
    fi
done

echo ""
echo -e "  ${GREEN}${BOLD}Hooks installed successfully!${NC}"
echo ""

# Verify hooks are executable
echo -e "  ${CYAN}Verifying hooks...${NC}"
for hook in "${HOOKS[@]}"; do
    TARGET_FILE="${HOOKS_TARGET}/${hook}"
    if [ -x "${TARGET_FILE}" ]; then
        echo -e "    ${GREEN}✓${NC} ${hook} is executable"
    else
        echo -e "    ${RED}✗${NC} ${hook} is NOT executable"
    fi
done

echo ""
echo -e "  ${YELLOW}Tip:${NC} To bypass hooks temporarily, use:"
echo -e "    git commit --no-verify"
echo -e "    git push --no-verify"
echo ""

# Optionally, also set up core.hooksPath for shared hooks
echo -e "  ${CYAN}Configuring git to use shared hooks path...${NC}"
git config core.hooksPath "${HOOKS_SOURCE}" 2>/dev/null || true
echo -e "  ${GREEN}✓${NC} Git configured to use ${HOOKS_SOURCE}"

echo ""
echo -e "  ${GREEN}${BOLD}Done! Hooks are now active.${NC}"