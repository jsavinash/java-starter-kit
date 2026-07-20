# Contributing to Java Starter Kit

Thank you for your interest in contributing! This document provides guidelines and best practices for contributing to this monorepo.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Commit Message Convention](#commit-message-convention)
- [Code Quality Standards](#code-quality-standards)
- [Testing Requirements](#testing-requirements)
- [Pull Request Process](#pull-request-process)
- [Monorepo Structure](#monorepo-structure)

## Code of Conduct

- Be respectful and inclusive
- Welcome newcomers and help them get started
- Focus on constructive feedback
- Respect differing viewpoints and experiences

## Getting Started

### Prerequisites

- **Java 25** (JDK)
- **Gradle 8.x** (wrapper included)
- **Git** with hooks enabled
- **IDE**: IntelliJ IDEA / Eclipse / VS Code

### Initial Setup

```bash
# Clone the repository
git clone <repository-url>
cd java-starter-kit-final

# Install git hooks (REQUIRED)
bash scripts/install-hooks.sh

# Verify installation
git config --get core.hooksPath
# Should output: /path/to/java-starter-kit-final/.githooks

# Build all modules
./gradlew build

# Run tests
./gradlew test
```

## Development Workflow

### 1. Create a Feature Branch

```bash
# Always branch from 'develop'
git checkout develop
git pull origin develop
git checkout -b feat/your-feature-name

# Or for bug fixes
git checkout -b fix/issue-description
```

**Branch Naming Convention:**
- `feat/` - New features
- `fix/` - Bug fixes
- `refactor/` - Code refactoring
- `docs/` - Documentation changes
- `test/` - Adding or updating tests
- `chore/` - Maintenance tasks

### 2. Make Changes

Follow the project structure:
- **Shared libraries**: `shared/` directory
- **Microservices**: `apps/micro-services/` directory
- **Build logic**: `build-logic/` directory

### 3. Run Quality Checks Locally

```bash
# Format code
./gradlew spotlessApply

# Run all quality checks
./gradlew qualityCheck

# Run tests
./gradlew test

# Check coverage
./gradlew jacocoTestCoverageVerification
```

### 4. Commit Changes

```bash
# Stage changes
git add .

# Commit (pre-commit and commit-msg hooks will run automatically)
git commit -m "feat(auth): add OAuth2 login endpoint"
```

**The commit-msg hook will validate your message format.**

### 5. Push and Create PR

```bash
# Push to your branch
git push origin feat/your-feature-name

# Create a Pull Request to 'develop' branch
```

## Commit Message Convention

This project follows [Conventional Commits](https://www.conventionalcommits.org/) specification.

### Format

```
<type>(<scope>): <description>
```

### Types

- `feat` - A new feature
- `fix` - A bug fix
- `docs` - Documentation changes
- `style` - Code style changes (formatting, etc.)
- `refactor` - Code refactoring
- `perf` - Performance improvements
- `test` - Adding or updating tests
- `build` - Build system changes
- `ci` - CI configuration changes
- `chore` - Other changes

### Examples

```
feat(auth): add OAuth2 login endpoint
fix(cart): resolve NPE in empty cart
refactor(api): extract common error handler
docs(readme): update setup instructions
test(user): add unit tests for UserService
```

### Breaking Changes

Add `!` after the type/scope:

```
feat(api)!: change response format for all endpoints
```

## Code Quality Standards

### Java

- **Style**: Google Java Style Guide (enforced by Checkstyle)
- **Formatting**: google-java-format (via Spotless)
- **Static Analysis**: PMD, Checkstyle
- **Line Length**: 120 characters max
- **Imports**: No wildcard imports, organized alphabetically

### Kotlin

- **Style**: Official Kotlin coding conventions
- **Formatting**: ktlint (via Spotless)
- **Static Analysis**: Detekt
- **Line Length**: 120 characters max

### General

- **No commented-out code**
- **No debug logging** (use proper logging frameworks)
- **No hardcoded values** (use constants)
- **Meaningful variable names**
- **Small, focused methods** (max 60 lines)
- **Comprehensive tests** (min 80% coverage)

## Testing Requirements

### Unit Tests

- All new features must include unit tests
- All bug fixes should include regression tests
- Minimum coverage: **80% line coverage**, **60% branch coverage**

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :apps:micro-services:user:test

# Run with coverage
./gradlew test jacocoTestReport

# View coverage report
open apps/micro-services/user/build/reports/jacoco/test/html/index.html
```

### Test Structure

```
src/test/java/
├── unit/
│   └── UserServiceTest.java
├── integration/
│   └── UserControllerIT.java
└── fixtures/
    └── TestDataFactory.java
```

## Pull Request Process

### Before Submitting

1. ✅ All quality checks pass: `./gradlew qualityCheck`
2. ✅ All tests pass: `./gradlew test`
3. ✅ Code coverage meets thresholds: `./gradlew jacocoTestCoverageVerification`
4. ✅ No merge conflicts with `develop`
5. ✅ Commit messages follow convention

### PR Description Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing performed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex logic
- [ ] Documentation updated
- [ ] No new warnings introduced
```

### Review Process

1. At least one approval required
2. All CI checks must pass
3. No unresolved conversations
4. Squash and merge to `develop`

## Monorepo Structure

```
java-starter-kit-final/
├── apps/
│   └── micro-services/          # All microservices
│       ├── api-gateway/
│       ├── user/
│       ├── order/
│       └── ...
├── shared/                      # Shared libraries
│   ├── configurations/
│   ├── constants/
│   ├── entities/
│   ├── enums/
│   └── utility/
├── build-logic/                 # Gradle build plugins
│   ├── custom-plugins/
│   ├── springboot-app/
│   ├── java-app/
│   └── java-lib/
├── platforms/                   # BOMs (Bill of Materials)
│   ├── springboot/
│   ├── test/
│   ├── web/
│   └── android/
├── aggregation/                 # Report aggregation
│   └── test-coverage/
├── config/                      # Tool configurations
│   ├── checkstyle/
│   ├── detekt/
│   └── pmd/
├── .githooks/                   # Git hooks
├── scripts/                     # Utility scripts
└── .github/workflows/           # CI/CD pipelines
```

### Adding a New Microservice

1. Create directory: `apps/micro-services/your-service/`
2. Create `build.gradle.kts`:
   ```kotlin
   plugins {
       id("com.custom-plugins.springboot-app")
   }

   group = "com.starter.services"
   version = "0.0.1"
   ```
3. Add to `apps/micro-services/settings.gradle.kts`:
   ```kotlin
   include("your-service")
   ```
4. Create source directories:
   ```
   src/main/java/com/starter/services/your-service/
   src/test/java/com/starter/services/your-service/
   ```

### Adding a New Shared Library

1. Create directory: `shared/your-library/`
2. Create `build.gradle.kts`:
   ```kotlin
   plugins {
       id("com.custom-plugins.java-library")
   }

   group = "com.starter.shared"
   version = "0.0.1"
   ```
3. Add to `shared/settings.gradle.kts`:
   ```kotlin
   include("your-library")
   ```

## Useful Commands

```bash
# Build everything
./gradlew build

# Run all quality checks
./gradlew qualityCheck

# Format code
./gradlew spotlessApply

# Check formatting
./gradlew spotlessCheck

# Run tests
./gradlew test

# Generate coverage report
./gradlew jacocoTestReport

# Check dependencies for vulnerabilities
./gradlew dependencyCheckAnalyze

# Clean build
./gradlew clean

# Deep clean (includes caches)
./gradlew deepClean

# List all microservices
./gradlew :apps:micro-services:listServices

# Build specific service
./gradlew :apps:micro-services:user:build
```

## Troubleshooting

### Hooks Not Running

```bash
# Reinstall hooks
bash scripts/install-hooks.sh

# Verify installation
git config --get core.hooksPath
```

### Build Failures

```bash
# Clean and rebuild
./gradlew clean build --refresh-dependencies

# Clear Gradle caches
rm -rf .gradle/ build/
./gradlew build
```

### Test Failures

```bash
# Run with more verbose output
./gradlew test --info --stacktrace

# Run specific test
./gradlew test --tests "com.starter.services.user.UserServiceTest"
```

## Questions?

- Check existing [Issues](https://github.com/your-repo/issues)
- Review [Documentation](https://github.com/your-repo/wiki)
- Reach out to the team

---

**Happy Coding! 🚀**