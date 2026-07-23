# Java Starter Kit

[![Java](https://img.shields.io/badge/Java-25-blue)](https://www.oracle.com/java/technologies/downloads/)
[![Gradle](https://img.shields.io/badge/Gradle-9.x-green)](https://gradle.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)
[![Composite Builds](https://img.shields.io/badge/Composite%20Builds-8-blueviolet)](settings.gradle.kts)

A production-ready, enterprise-grade monorepo starter kit for building scalable microservices with Java, Spring Boot, and Gradle. Includes comprehensive code quality checks, auto-fix tooling, platform BOMs, and CI/CD pipelines.

## ✨ Features

### 🏗️ Architecture
- **Monorepo Structure**: Centralized management of microservices and shared libraries
- **Composite Builds**: 8 isolated Gradle builds for independent versioning and parallel execution
- **Convention Plugins**: Reusable Gradle plugins for consistent build configuration
- **Platform BOMs**: Bill of Materials for centralized dependency version management

### 🔍 Code Quality
- **Auto-Fix Tooling**: Quality failures automatically trigger fixes (optional, per-plugin)
  - `spotlessCheck` → `spotlessApply` (auto-format)
  - `checkstyleMain` → `spotlessApply` + `checkstyleAutoFix` (formatting + common violations)
  - `detektMain` → `detektAutoCorrect` (auto-correct)
- **Pre-commit Hooks**: Automated checks before every commit
  - Branch protection (no direct commits to main/develop)
  - Staged file validation (merge conflicts, large files, forbidden binaries)
  - Commit message validation (Conventional Commits)
  - Code formatting (Spotless)
  - Static analysis (Checkstyle, Detekt, PMD)
  - Unit tests with coverage verification
  - Dependency vulnerability scanning
- **Pre-push Hooks**: Comprehensive checks before pushing
  - Full test suite execution
  - Code quality checks across all modules
  - Commit history inspection
- **CI/CD Pipeline**: GitHub Actions with parallel jobs
  - Code quality checks
  - Unit and integration tests
  - Dependency vulnerability scanning
  - Quality gate enforcement

### 📊 Testing & Coverage
- **JUnit 5**: Modern testing framework
- **JaCoCo**: Code coverage with strict thresholds
  - 80% line/instruction coverage
  - 60% branch coverage
  - 70% method coverage
  - 80% class coverage
- **Test Retry**: Automatic retry for flaky tests
- **Parallel Execution**: Fast test execution

### 🎨 Code Formatting
- **Spotless**: Automated code formatting
  - Java: Google Java Format
  - Kotlin: ktlint
  - Gradle: ktlint
  - YAML/JSON/Markdown
- **EditorConfig**: Consistent editor settings across IDEs

### 🔒 Security
- **OWASP Dependency Check**: Vulnerability scanning
- **PMD Security Rules**: Static security analysis
- **Checkstyle Security**: Code security patterns

### ⚡ Performance
- **Build Cache**: Local and remote caching for faster builds
- **Parallel Execution**: Concurrent task execution
- **Configuration on Demand**: Faster configuration phase
- **Incremental Compilation**: Kotlin and Java incremental builds
- **Gradle Daemon**: Persistent build process

## 📁 Project Structure

```
java-starter-kit/
├── apps/
│   └── micro-services/              # Microservices (17 services)
│       ├── api-gateway/
│       ├── config-server/
│       ├── service-discovery/
│       ├── user/
│       ├── item/
│       ├── item-management/
│       ├── product/
│       ├── review-and-ratings/
│       ├── inventory/
│       ├── recommendations/
│       ├── offers/
│       ├── cart/
│       ├── order/
│       ├── archival/
│       ├── notification/
│       ├── serviceability/
│       └── payment/
├── shared/                          # Shared libraries
│   ├── configurations/              # Configuration constants
│   ├── constants/                   # Common constants
│   ├── entities/                    # Domain entities
│   ├── enums/                       # Enumerations
│   └── utility/                     # Utility classes
├── build-logic/                     # Gradle build plugins (convention & custom)
│   ├── custom-plugins/              # Precompiled script plugins
│   │   ├── com.custom-plugins.combined.gradle.kts
│   │   ├── com.custom-plugins.code-formatter.gradle.kts
│   │   ├── com.custom-plugins.detekt.gradle.kts
│   │   ├── com.custom-plugins.jacoco.gradle.kts
│   │   ├── com.custom-plugins.pmd.gradle.kts
│   │   ├── com.custom-plugins.githooks.gradle.kts
│   │   └── com.custom-plugins.auto-fix.gradle.kts
│   ├── springboot-app/              # Spring Boot convention plugin
│   ├── java-app/                    # Java application convention plugin
│   ├── java-lib/                    # Java library convention plugin
│   └── report-aggregation/          # Report aggregation plugin
├── platforms/                       # BOM composite build
│   ├── springboot/                  # Spring Boot BOM
│   ├── test/                        # Testing BOM
│   ├── web/                         # Web/Ktor BOM
│   └── android/                     # Android platform BOM
├── aggregation/                     # Report aggregation (composite build)
│   └── test-coverage/               # JaCoCo coverage aggregation
├── educational-resources/           # Learning resources (composite build)
│   ├── java-programming/
│   └── system-design/
├── excalidraw/                      # Architecture diagrams (Excalidraw)
├── infra/                           # Infrastructure (composite build)
│   ├── app/
│   └── assets/
├── packages/                        # Algorithms, data structures (composite build)
│   ├── algorithms/
│   ├── concepts/
│   └── data-structure/
├── config/                          # Tool configurations
│   ├── checkstyle/                  # Checkstyle rules
│   ├── detekt/                      # Detekt rules
│   └── pmd/                         # PMD rules
├── .githooks/                       # Git hooks (auto-installed)
│   ├── pre-commit                   # Pre-commit checks
│   ├── commit-msg                   # Commit message validation
│   ├── pre-push                     # Pre-push checks
│   └── lib/                         # Shared hook library
├── scripts/                         # Utility scripts
│   └── install-hooks.sh             # Hook installation script
├── .github/workflows/               # CI/CD pipelines
│   └── ci.yml                       # GitHub Actions workflow
├── gradle/                          # Gradle wrapper and version catalog
│   ├── wrapper/
│   └── libs.versions.toml           # Dependency versions
├── build.gradle.kts                 # Root build file (aggregation tasks)
├── settings.gradle.kts              # Root settings (8 composite builds)
├── gradle.properties                # Gradle properties
├── .editorconfig                    # Editor configuration
├── .gitignore                       # Git ignore rules
├── .sdkmanrc                        # SDKMAN configuration
├── ARCHITECTURE.md                  # Architecture documentation
├── CONTRIBUTING.md                  # Contribution guidelines
└── MONOREPO_IMPROVEMENTS.md         # Improvement tracking
```

## 🚀 Quick Start

### Prerequisites

- **Java 25** (JDK)
- **Git**
- **IDE**: IntelliJ IDEA (recommended) / Eclipse / VS Code

### Installation

```bash
# Clone the repository
git clone <repository-url>
cd java-starter-kit

# Build everything (hooks are auto-installed on first build)
./gradlew build

# Or manually install hooks if needed
bash scripts/install-hooks.sh

# Verify hooks are installed
git config --get core.hooksPath
# Expected output: /path/to/java-starter-kit/.githooks
```

**Note**: Git hooks are automatically installed when you run `./gradlew build`, `./gradlew compileKotlin`, or `./gradlew compileJava`. No manual step required.

## 📋 Available Commands

### Build Commands

```bash
# Build all modules
./gradlew build

# Run all tests
./gradlew test

# Clean build
./gradlew clean

# Deep clean (includes caches)
./gradlew deepClean

# Full build with quality checks
./gradlew fullBuild
```

### Quality Commands

```bash
# Run all quality checks across all modules
./gradlew qualityCheck
./gradlew qualityGate

# Auto-fix all issues (spotless + checkstyle + detekt)
./gradlew qualityFix
./gradlew checkstyleAutoFix
./gradlew spotlessApply

# Run individual checks
./gradlew spotlessCheck
./gradlew checkstyleMain
./gradlew detektMain
./gradlew pmdMain

# Run individual fixes
./gradlew spotlessApply
./gradlew checkstyleAutoFix
./gradlew detektAutoCorrect
```

### Microservice Commands

```bash
# Build specific service
./gradlew :micro-services:user:build

# Run quality fix on specific service
./gradlew :micro-services:user:qualityFix

# Run tests for specific module
./gradlew :micro-services:user:test

# Run specific test class
./gradlew :micro-services:user:test --tests "com.starter.services.user.UserServiceTest"
```

### Utility Commands

```bash
# List all available tasks
./gradlew tasks

# Check dependencies
./gradlew dependencyCheckAnalyze

# View dependency tree
./gradlew :micro-services:api-gateway:dependencies

# Build with scan (shares build insights)
./gradlew build --scan
```

## 🏗️ Architecture

### Build-Logic Convention Plugins

The `build-logic/` directory contains all reusable Gradle plugins organized as a composite build:

```
build-logic/
├── custom-plugins/                # Precompiled script plugins (as .gradle.kts files)
│   ├── com.custom-plugins.combined.gradle.kts
│   ├── com.custom-plugins.code-formatter.gradle.kts
│   ├── com.custom-plugins.detekt.gradle.kts
│   ├── com.custom-plugins.jacoco.gradle.kts
│   ├── com.custom-plugins.pmd.gradle.kts
│   ├── com.custom-plugins.githooks.gradle.kts
│   └── com.custom-plugins.auto-fix.gradle.kts
├── springboot-app/                # Spring Boot convention plugin
├── java-app/                      # Java application convention plugin
├── java-lib/                      # Java library convention plugin
└── report-aggregation/            # Report aggregation plugin
```

**Plugin dependency hierarchy**:
```
springboot-app ──┐
java-app ────────┤── combined ──┬── code-formatter
java-lib ────────┘              ├── detekt
                                 ├── jacoco
                                 ├── pmd
                                 ├── githooks
                                 └── auto-fix
```
report-aggregation (standalone, no quality checks)

Every project using `springboot-app`, `java-app`, or `java-lib` automatically gets all quality tools + auto-fix + git hooks installation.

### Platform BOMs

The `platforms/` composite build provides curated Bill of Materials for dependency version management:

```
platforms/
├── springboot/     → com.starter.platforms:springboot-platform:1.0.0
├── test/           → com.starter.platforms:test-platform:1.0.0
├── web/            → com.starter.platforms:web-platform:1.0.0
└── android/        → com.starter.platforms:android-platform:1.0.0
```

BOMs are automatically consumed by convention plugins:
- `springboot-app` → `implementation(platform("com.starter.platforms:springboot-platform:1.0.0"))`
- Individual libraries override via `libs.versions.toml` as needed

### Composite Builds (8 Total)

The root `settings.gradle.kts` composes multiple isolated Gradle builds for independent versioning, parallel execution, and build isolation:

```
root (java-starter-kit)
├── build-logic/              ← Convention & custom plugins
├── apps/micro-services/      ← 17 microservice modules
├── shared/                   ← Shared libraries
├── platforms/                ← BOM definitions
├── infra/                    ← Infrastructure definitions
├── aggregation/              ← Aggregated reports
├── packages/                 ← Algorithm & data structure packages
└── educational-resources/    ← Learning resources
```

Each composite build has its own `settings.gradle.kts`, enabling isolated dependency resolution, independent versioning, and parallel CI execution.

## 🔧 Configuration

### Git Hooks

Git hooks are **auto-installed** on every build via the `com.custom-plugins.githooks` plugin. Three hooks are active:

1. **pre-commit**: Quality checks before commit
   - Branch protection
   - Staged file validation (merge conflicts, large files, secrets)
   - Commit message validation (Conventional Commits)
   - Smart-scoped code formatting (Spotless)
   - Checkstyle + Detekt + PMD static analysis
   - Unit tests (only affected modules)

2. **commit-msg**: Validates commit message format
   - Conventional Commits format: `type(scope): description`
   - JIRA ticket format: `PROJ-123 description`
   - Message length (max 72 chars)

3. **pre-push**: Comprehensive checks before push
   - Full test suite
   - Code quality checks across all modules
   - Commit history inspection

### Auto-Fix Behavior

The `com.custom-plugins.auto-fix` plugin automatically applies fixes when quality checks fail:

| Check Failure | Auto-Fix Applied |
|--------------|------------------|
| `spotlessCheck` | `spotlessApply` |
| `checkstyleMain` | `spotlessApply` + `checkstyleAutoFix` |
| `detektMain` | `detektAutoCorrect` |
| `detektTest` | `detektTestAutoCorrect` |

The `checkstyleAutoFix` custom task fixes common violations programmatically:
- Removes trailing whitespace
- Ensures files end with newline
- Replaces tabs with 4 spaces
- Removes consecutive blank lines

Run `./gradlew qualityFix` to apply all fixes manually.

### Quality Thresholds

- **Code Coverage**: 80% line, 60% branch, 70% method, 80% class
- **Checkstyle**: 0 errors, 0 warnings
- **Detekt**: Default config with custom ruleset
- **PMD**: Best practices + error-prone rulesets
- **Spotless**: 0 formatting issues

### Build Optimization

- **Parallel Execution**: Enabled by default
- **Build Cache**: Local cache (30 days retention)
- **Configuration on Demand**: Enabled
- **Gradle Daemon**: Enabled
- **Incremental Compilation**: Enabled for Kotlin and Java

## 🧪 Testing

### Test Structure

```
src/test/
├── java/
│   ├── unit/                      # Unit tests
│   │   └── UserServiceTest.java
│   ├── integration/               # Integration tests
│   │   └── UserControllerIT.java
│   └── fixtures/                  # Test fixtures
│       └── TestDataFactory.java
└── resources/
    └── application-test.yml
```

### Writing Tests

```java
// Unit Test Example
class UserServiceTest {
    @Test
    void shouldCreateUser() {
        // Given
        User user = new User("john", "john@example.com");
        
        // When
        User created = userService.create(user);
        
        // Then
        assertThat(created.getId()).isNotNull();
    }
}

// Integration Test Example
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void shouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }
}
```

## 🤝 Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines.

### Quick Contribution Steps

1. Fork the repository
2. Create a feature branch: `git checkout -b feat/your-feature`
3. Install hooks (auto-done on first build): `./gradlew build`
4. Make changes
5. Run quality checks: `./gradlew qualityCheck`
6. Auto-fix issues: `./gradlew qualityFix`
7. Commit with conventional commit message: `git commit -m "feat(scope): description"`
8. Push and create Pull Request

## 📊 CI/CD Pipeline

The GitHub Actions workflow runs on every push and PR:

1. **Quality Job**: Spotless, Checkstyle, Detekt, PMD
2. **Test Job**: Unit tests with coverage
3. **Dependency Check**: OWASP vulnerability scan
4. **Build Job**: Full build with all checks
5. **Quality Gate**: Final verification

## 📈 Coverage Reports

Coverage reports are generated after test execution:

```bash
# Generate coverage report
./gradlew jacocoTestReport

# View report
open micro-services/user/build/reports/jacoco/test/html/index.html
```

Reports are also available in CI as artifacts.

## 🔍 Static Analysis

### Checkstyle (Java)

Enforces Google Java Style Guide:
- Naming conventions
- Import organization
- Code formatting
- Javadoc comments

### Detekt (Kotlin)

Kotlin static analysis:
- Code complexity
- Naming conventions
- Style violations
- Potential bugs

### PMD (Java)

Advanced static analysis:
- Best practices
- Code smells
- Security vulnerabilities
- Performance issues

## 📝 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [Spotless](https://github.com/diffplug/spotless)
- [Detekt](https://detekt.dev/)
- [Checkstyle](https://checkstyle.org/)
- [PMD](https://pmd.github.io/)
- [JaCoCo](https://www.jacoco.org/)

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/jsavinash/java-starter-kit/issues)
- **Discussions**: [GitHub Discussions](https://github.com/jsavinash/java-starter-kit/discussions)

---

**Built with ❤️ for the Java community**