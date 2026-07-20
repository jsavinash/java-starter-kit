# Java Starter Kit

[![Java](https://img.shields.io/badge/Java-25-blue)](https://www.oracle.com/java/technologies/downloads/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-green)](https://gradle.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

A production-ready, enterprise-grade monorepo starter kit for building scalable microservices with Java, Spring Boot, and Gradle. Includes comprehensive code quality checks, testing infrastructure, and CI/CD pipelines.

## ✨ Features

### 🏗️ Architecture
- **Monorepo Structure**: Centralized management of microservices and shared libraries
- **Composite Builds**: Isolated Gradle builds for microservices, shared libraries, and aggregation
- **Convention Plugins**: Reusable Gradle plugins for consistent build configuration
- **Platform BOMs**: Bill of Materials for dependency management

### 🔍 Code Quality
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
java-starter-kit-final/
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
├── build-logic/                     # Gradle build plugins
│   ├── custom-plugins/              # Custom quality plugins
│   │   ├── code-formatter           # Spotless configuration
│   │   ├── detekt                   # Kotlin static analysis
│   │   ├── checkstyle               # Java static analysis
│   │   ├── pmd                      # Java static analysis
│   │   ├── jacoco                   # Code coverage
│   │   └── combined                 # Combined quality gate
│   ├── springboot-app/              # Spring Boot plugin
│   ├── java-app/                    # Java application plugin
│   └── java-lib/                    # Java library plugin
├── platforms/                       # BOMs (Bill of Materials)
│   ├── springboot/                  # Spring Boot platform
│   ├── test/                        # Testing platform
│   ├── web/                         # Web/Ktor platform
│   └── android/                     # Android platform
├── aggregation/                     # Report aggregation
│   └── test-coverage/               # JaCoCo coverage aggregation
├── config/                          # Tool configurations
│   ├── checkstyle/                  # Checkstyle rules
│   ├── detekt/                      # Detekt rules
│   └── pmd/                         # PMD rules
├── .githooks/                       # Git hooks
│   ├── pre-commit                   # Pre-commit checks
│   ├── commit-msg                   # Commit message validation
│   └── pre-push                     # Pre-push checks
├── scripts/                         # Utility scripts
│   └── install-hooks.sh             # Hook installation script
├── .github/workflows/               # CI/CD pipelines
│   └── ci.yml                       # GitHub Actions workflow
├── gradle/                          # Gradle wrapper and version catalog
│   ├── wrapper/
│   └── libs.versions.toml           # Dependency versions
├── build.gradle.kts                 # Root build file
├── settings.gradle.kts              # Root settings
├── gradle.properties                # Gradle properties
├── .editorconfig                    # Editor configuration
├── .gitignore                       # Git ignore rules
└── CONTRIBUTING.md                  # Contribution guidelines
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
cd java-starter-kit-final

# Install git hooks (REQUIRED)
bash scripts/install-hooks.sh

# Verify hooks are installed
git config --get core.hooksPath
# Expected output: /path/to/java-starter-kit-final/.githooks

# Build all modules
./gradlew build

# Run all tests
./gradlew test

# Run quality checks
./gradlew qualityCheck
```

## 📋 Available Commands

### Build Commands

```bash
# Build all modules
./gradlew build

# Build specific service
./gradlew :apps:micro-services:user:build

# Clean build
./gradlew clean

# Deep clean (includes caches)
./gradlew deepClean
```

### Quality Commands

```bash
# Run all quality checks
./gradlew qualityCheck

# Format code
./gradlew spotlessApply

# Check formatting
./gradlew spotlessCheck

# Run static analysis
./gradlew checkstyleMain detektMain pmdMain

# Run quality gate
./gradlew qualityGate
```

### Testing Commands

```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :apps:micro-services:user:test

# Run with coverage
./gradlew test jacocoTestReport

# Verify coverage thresholds
./gradlew jacocoTestCoverageVerification

# Run specific test class
./gradlew test --tests "com.starter.services.user.UserServiceTest"
```

### Utility Commands

```bash
# List all microservices
./gradlew :apps:micro-services:listServices

# Check dependencies
./gradlew dependencyCheckAnalyze

# Build with scan
./gradlew build --scan
```

## 🔧 Configuration

### Git Hooks

The project uses three git hooks:

1. **pre-commit**: Runs quality checks before commit
   - Branch protection
   - Staged file validation
   - Code formatting
   - Static analysis
   - Unit tests

2. **commit-msg**: Validates commit message format
   - Conventional Commits format
   - JIRA ticket format
   - Message length (max 72 chars)

3. **pre-push**: Runs comprehensive checks before push
   - Full test suite
   - Code quality checks
   - Commit history inspection

### Quality Thresholds

- **Code Coverage**: 80% line, 60% branch
- **Checkstyle**: 0 errors, 0 warnings
- **Detekt**: Max 10 issues
- **PMD**: 0 violations
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
3. Install hooks: `bash scripts/install-hooks.sh`
4. Make changes and run quality checks: `./gradlew qualityCheck`
5. Commit with conventional commit message: `git commit -m "feat(scope): description"`
6. Push and create Pull Request

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
open apps/micro-services/user/build/reports/jacoco/test/html/index.html
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

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [Spotless](https://github.com/diffplug/spotless)
- [Detekt](https://detekt.dev/)
- [Checkstyle](https://checkstyle.org/)
- [PMD](https://pmd.github.io/)
- [JaCoCo](https://www.jacoco.org/)

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/your-repo/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-repo/discussions)
- **Email**: your-email@example.com

---

**Built with ❤️ for the Java community**