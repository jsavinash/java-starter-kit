# Java Starter Kit - Monorepo

A comprehensive Java starter kit monorepo using **Java 25 (Amazon Corretto)** and **Gradle 9.6.1** with centralized dependency management and custom convention plugins.

## 🏗️ Architecture

### Monorepo Structure
```
java-starter-kit/
├── build-logic/              # Custom convention plugins
│   ├── custom-plugins/       # Combined plugin (Spotless, Checkstyle, PMD, JaCoCo)
│   ├── java-app/             # Java application plugin
│   ├── java-lib/             # Java library plugin
│   ├── springboot-app/       # Spring Boot application plugin
│   └── report-aggregation/   # Report aggregation plugin
├── apps/micro-services/      # Microservices composite build
│   ├── api-gateway/
│   ├── user/
│   ├── product/
│   ├── order/
│   ├── inventory/
│   └── ...
├── shared/                   # Shared libraries composite build
│   ├── configurations/
│   ├── constants/
│   ├── entities/
│   ├── enums/
│   └── utility/
├── packages/                 # Reusable packages
│   ├── algorithms/
│   ├── concepts/
│   └── data-structure/
├── platforms/                # Platform-specific code
│   ├── android/
│   ├── springboot/
│   ├── test/
│   └── web/
├── educational-resources/    # Educational pattern implementations
│   ├── java-programming/
│   └── system-design/
│       ├── patterns/         # Design patterns (100+ implementations)
│       ├── theory/
│       └── high-level-design/
├── aggregation/              # Build aggregation
│   └── test-coverage/        # Coverage reports
└── config/                   # Configuration files
    ├── checkstyle/
    └── pmd/
```

### Composite Builds
The monorepo uses Gradle composite builds for isolation and better caching:

- **apps/micro-services** - Microservices applications
- **shared** - Shared libraries
- **packages** - Reusable packages
- **platforms** - Platform BOMs
- **educational-resources** - Educational content
- **aggregation** - Build aggregation

## 🚀 Quick Start

### Prerequisites

- **Java 25** (Amazon Corretto 25.0.4 or compatible)
- **Gradle 9.6.1** (wrapper included)
- **Git** (for version control)

### Installation

```bash
# Clone the repository
git clone https://github.com/jsavinash/java-starter-kit.git
cd java-starter-kit

# Verify Java version
java -version  # Should show 25.x.x

# Verify Gradle wrapper
./gradlew --version
```

## 📦 Build Commands

### Root-Level Builds

```bash
# Clean and build all modules (parallel execution)
./gradlew clean build --parallel

# Run quality checks across all modules
./gradlew qualityCheck

# Apply code formatting (Spotless)
./gradlew spotlessApply

# Full build with quality checks
./gradlew fullBuild

# Generate test reports
./gradlew testReport

# Generate documentation (Dokka)
./gradlew generateDocs

# Deep clean (removes all build artifacts and caches)
./gradlew deepClean
```

### Module-Specific Builds

```bash
# Build specific composite build
./gradlew :apps:micro-services:build

# Build specific module
./gradlew :apps:micro-services:user:build

# Run tests for specific module
./gradlew :apps:micro-services:user:test

# Build educational resources
./gradlew :educational-resources:system-design:patterns:builder:build
```

### Docker Operations

```bash
# Build Docker images for all microservices
./gradlew dockerBuildAll

# Publish Docker images
./gradlew dockerPublishAll
```

### Dependency Management

```bash
# Check for dependency updates
./gradlew dependencyUpdates

# Analyze dependencies
./gradlew analyzeAllDependencies

# Validate dependency versions
./gradlew validateAllDependencyVersions
```

## 🔧 Configuration

### Version Catalog

All dependencies are centralized in `gradle/libs.versions.toml`:

```toml
[versions]
gradle = "9.6.1"
kotlin = "2.4.10"
springBoot = "4.0.1"
junitJupiter = "5.12.2"
lombokLibrary = "1.18.36"

[libraries]
h2 = { group = "com.h2database", name = "h2", version.ref = "h2" }
springboot-starter-web = { group = "org.springframework.boot", name = "spring-boot-starter-web", version.ref = "springBoot" }
junit-jupiter = { group = "org.junit.jupiter", name = "junit-jupiter", version.ref = "junitJupiter" }

[plugins]
springboot = { id = "org.springframework.boot", version.ref = "springBoot" }
spotless = { id = "com.diffplug.spotless", version.ref = "spotless" }
```

### Custom Convention Plugins

Apply convention plugins in your `build.gradle.kts`:

```kotlin
plugins {
    id("com.custom-plugins.java-app")  // For Java applications
    // OR
    id("com.custom-plugins.java-library")  // For Java libraries
    // OR
    id("com.custom-plugins.springboot-app")  // For Spring Boot apps
    alias(libs.plugins.lombok)
}

dependencies {
    // Use centralized dependencies
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    testImplementation(libs.junit.jupiter)
}
```

### Java Toolchain

Java 25 is configured centrally in convention plugins:

```kotlin
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
        vendor.set(JvmVendorSpec.AMAZON)
    }
}
```

## 🧪 Testing

```bash
# Run all tests
./gradlew test --parallel

# Run tests with coverage
./gradlew test jacocoTestReport

# View test reports
open aggregation/test-coverage/build/reports/tests/test/index.html
```

## 📊 Code Quality

### Static Analysis

```bash
# Run Checkstyle
./gradlew checkstyleMain checkstyleTest

# Run PMD
./gradlew pmdMain pmdTest

# Run Spotless (formatting check)
./gradlew spotlessCheck

# Auto-format code
./gradlew spotlessApply
```

### Quality Gates

```bash
# Run all quality checks
./gradlew qualityCheck

# Generate JaCoCo coverage report
./gradlew jacocoTestReport
```

## 📚 Documentation

### Generating Documentation

```bash
# Generate HTML documentation
./gradlew generateDocs

# Generate all formats
./gradlew generateAllDocs
```

### Design Patterns

The `educational-resources/system-design/patterns` directory contains implementations of 100+ design patterns:

- **Creational**: Abstract Factory, Builder, Factory Method, Prototype, Singleton
- **Structural**: Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
- **Behavioral**: Chain of Responsibility, Command, Iterator, Mediator, Observer, Strategy, Template Method
- **Architectural**: Layered, Hexagonal, Microservices, Monolithic, CQRS, Event-Driven

Each pattern includes:
- Production-ready implementation
- Unit tests
- README with examples

## 🔄 CI/CD

### GitHub Actions

The CI pipeline (`.github/workflows/ci.yml`) runs:

1. **Build** - Compile all modules
2. **Test** - Run all unit tests
3. **Quality** - Run Spotless, Checkstyle, PMD
4. **Coverage** - Generate JaCoCo reports
5. **Dependency Check** - Vulnerability scanning

### Optimized Build Commands for CI

```bash
# Fast build (skip tests)
./gradlew build -x test --parallel

# Build only impacted modules (requires git diff)
./gradlew build --parallel --continue

# Run tests only for changed modules
./gradlew affectedTests
```

## 🚀 Performance Optimization

### Build Cache

Build cache is enabled by default:
- Local cache: `.gradle/build-cache`
- Remote cache: Configure in `settings.gradle.kst`

### Parallel Execution

```bash
# Enable parallel execution
./gradlew build --parallel

# Enable configuration cache (requires Gradle 8.0+)
./gradlew build --configuration-cache
```

### Gradle Properties

Key properties in `gradle.properties`:

```properties
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configuration-cache=false
org.gradle.jvmargs=-Xmx4g -XX:MaxPermSize=512m
```

## 📦 Module Dependency Graph

### Core Libraries (No external dependencies)
```
shared/
├── constants
├── enums
└── utility
```

### Application Modules (Depend on core libraries)
```
apps/micro-services/
├── user (depends on: shared, entities)
├── product (depends on: shared, entities)
└── order (depends on: shared, entities)
```

### Educational Modules (Standalone)
```
educational-resources/system-design/patterns/
├── builder (standalone)
├── factory (standalone)
├── singleton (standalone)
└── ...
```

## 🛠️ Development Workflow

### Adding a New Module

1. Create module directory
2. Create `build.gradle.kts`:
```kotlin
plugins {
    id("com.custom-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.starter"
version = "1.0.0"

application {
    mainClass.set("com.starter.Main")
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    testImplementation(libs.junit.jupiter)
}
```

3. Apply centralized dependencies from `libs.versions.toml`
4. Build and test: `./gradlew :your-module:build`

### Adding a New Dependency

1. Add version to `gradle/libs.versions.toml`:
```toml
[versions]
newLibrary = "1.0.0"

[libraries]
new-library = { group = "com.example", name = "new-library", version.ref = "newLibrary" }
```

2. Use in build scripts:
```kotlin
implementation(libs.new.library)
```

## 📝 License

[Add your license here]

## 🤝 Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## 📖 References

- [Gradle 9.6.1 Documentation](https://docs.gradle.org/9.6.1/)
- [Spring Boot 4.0.1 Documentation](https://docs.spring.io/spring-boot/docs/4.0.1/reference/html/)
- [Amazon Corretto 25](https://docs.aws.amazon.com/corretto/)
- [Version Catalog Documentation](https://docs.gradle.org/9.6.1/userguide/platforms.html)
- [Convention Plugins](https://docs.gradle.org/9.6.1/userguide/custom_plugins.html)