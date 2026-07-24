# Java Starter Kit - Architecture Documentation

## Overview

This document provides a deep dive into the architecture and design decisions of the Java Starter Kit monorepo, which uses **8 composite builds**, **8 custom Gradle plugins**, and **4 platform BOMs** to deliver a scalable, production-ready microservices foundation.

## Table of Contents

1. [Monorepo Architecture](#monorepo-architecture)
2. [Build System Design](#build-system-design)
3. [Quality Gates](#quality-gates)
4. [Dependency Management](#dependency-management)
5. [CI/CD Pipeline](#cicd-pipeline)
6. [Performance Optimizations](#performance-optimizations)
7. [Security](#security)
8. [Best Practices](#best-practices)

## Monorepo Architecture

### High-Level Structure

```
java-starter-kit/
├── Composite Builds (8 isolated Gradle projects)
│   ├── build-logic/              # Reusable Gradle plugins
│   ├── apps/micro-services/      # 17 Spring Boot microservices
│   ├── shared/                   # 5 shared libraries
│   ├── platforms/                # BOM dependency management
│   ├── infra/                    # Infrastructure definitions
│   ├── aggregation/              # Report aggregation
│   ├── packages/                 # Algorithms & data structures
│   └── educational-resources/    # Learning resources
│
└── Root Project
    └── (orchestration & configuration)
```

### Composite Build Strategy (8 Total)

The monorepo uses **Gradle Composite Builds** to achieve isolation while maintaining unified management. Each composite build has its own `settings.gradle.kts`, enabling independent versioning, isolated dependency resolution, and parallel execution.

#### 1. **build-logic** (Composite Build)
- **Purpose**: Reusable convention and custom Gradle plugins
- **Benefits**: Centralized build configuration, consistent quality enforcement
- **Structure**:
  ```
  build-logic/
  ├── settings.gradle.kts
  ├── custom-plugins/               # 8 precompiled script plugins
  │   ├── com.custom-plugins.combined.gradle.kts
  │   ├── com.custom-plugins.code-formatter.gradle.kts
  │   ├── com.custom-plugins.detekt.gradle.kts
  │   ├── com.custom-plugins.jacoco.gradle.kts
  │   ├── com.custom-plugins.pmd.gradle.kts
  │   ├── com.custom-plugins.githooks.gradle.kts
  │   ├── com.custom-plugins.auto-fix.gradle.kts
  │   └── com.custom-plugins.javadoc2.gradle.kts
  ├── springboot-app/               # Spring Boot convention plugin
  ├── java-app/                     # Java application convention plugin
  ├── java-lib/                     # Java library convention plugin
  └── report-aggregation/           # Report aggregation plugin
  ```

#### 2. **apps/micro-services** (Composite Build)
- **Purpose**: Contains all 17 microservices
- **Benefits**: Independent versioning per service, isolated dependency resolution, parallel build execution
- **Structure**:
  ```
  apps/micro-services/
  ├── settings.gradle.kts          # Includes all services + composite includes
  ├── build.gradle.kts             # Aggregation tasks
  ├── api-gateway/
  ├── config-server/
  ├── service-discovery/
  ├── user/
  ├── item/
  ├── item-management/
  ├── product/
  ├── review-and-ratings/
  ├── inventory/
  ├── recommendations/
  ├── offers/
  ├── cart/
  ├── order/
  ├── archival/
  ├── notification/
  ├── serviceability/
  └── payment/
  ```
- **Composite Includes**: Imports `build-logic`, `shared`, and `platforms` with dependency substitution for BOMs

#### 3. **shared** (Composite Build)
- **Purpose**: Shared libraries consumed by microservices
- **Structure**:
  ```
  shared/
  ├── settings.gradle.kts
  ├── configurations/              # Configuration constants
  ├── constants/                   # Common constants
  ├── entities/                    # Domain entities
  ├── enums/                       # Enumerations
  └── utility/                     # Utility classes
  ```

#### 4. **aggregation** (Composite Build)
- **Purpose**: Aggregate reports (JaCoCo coverage) from all modules

#### 5. **platforms** (Composite Build)
- **Purpose**: Bill of Materials for dependency version management
- **4 Platforms**: springboot, test, web, android
- **Dependency Substitution**: Maven coordinates are substituted with local project artifacts

#### 6-8. **infra**, **packages**, **educational-resources** (Composite Builds)
- Infrastructure definitions, algorithm/data structure packages, and learning content

### Why Composite Builds?

| Aspect | Single Build | Composite Builds | This Project |
|--------|-------------|------------------|--------------|
| **Isolation** | ❌ Shared cache | ✅ Independent | ✅ Yes |
| **Parallelism** | ⚠️ Limited | ✅ Full | ✅ Yes |
| **Versioning** | ❌ Monolithic | ✅ Independent | ✅ Yes |
| **Build Time** | ⚠️ Slow | ✅ Fast | ✅ Optimized |
| **Complexity** | ✅ Simple | ⚠️ Complex | ✅ Managed |

## Build System Design

### Convention Plugins

The project uses **Gradle Convention Plugins** (precompiled script plugins) to enforce consistency across all modules.

#### Plugin Listing

| Plugin ID | File | Purpose |
|-----------|------|---------|
| `com.custom-plugins.combined` | `com.custom-plugins.combined.gradle.kts` | Aggregates all quality tools + auto-fix + githooks |
| `com.custom-plugins.code-formatter` | `com.custom-plugins.code-formatter.gradle.kts` | Spotless formatting (Java/Kotlin/YAML/JSON/Markdown/Gradle) |
| `com.custom-plugins.detekt` | `com.custom-plugins.detekt.gradle.kts` | Kotlin static analysis with detekt |
| `com.custom-plugins.jacoco` | `com.custom-plugins.jacoco.gradle.kts` | JaCoCo coverage with thresholds |
| `com.custom-plugins.pmd` | `com.custom-plugins.pmd.gradle.kts` | Java static analysis with PMD |
| `com.custom-plugins.githooks` | `com.custom-plugins.githooks.gradle.kts` | Auto-installs .githooks/ on build |
| `com.custom-plugins.auto-fix` | `com.custom-plugins.auto-fix.gradle.kts` | Auto-fix on quality check failures |
| `com.custom-plugins.javadoc2` | `com.custom-plugins.javadoc2.gradle.kts` | Enforces Javadoc documentation on public Java API |

#### Plugin Hierarchy

```
com.custom-plugins.combined (aggregates all quality tools)
├── applies: code-formatter, detekt, jacoco, pmd, githooks, auto-fix, javadoc2
│
Convention plugins (apply combined + language-specific config):
├── com.custom-plugins.java-library  → combined + java-library
├── com.custom-plugins.java-app      → combined + application
└── com.custom-plugins.springboot-app → combined + spring boot + checkstyle
```

#### Plugin Implementation Pattern

```kotlin
// build-logic/custom-plugins/src/main/kotlin/com.custom-plugins.combined.gradle.kts
plugins {
    java
    kotlin("jvm")
    id("checkstyle")
    id("com.custom-plugins.jacoco")
    id("com.custom-plugins.code-formatter")
    id("com.custom-plugins.detekt")
    id("com.custom-plugins.pmd")
    id("com.custom-plugins.githooks")
    id("com.custom-plugins.javadoc2")
    id("com.custom-plugins.auto-fix")
}

checkstyle {
    toolVersion = "10.21.4"
    configFile = rootProject.projectDir.parentFile.parentFile
        .resolve("config/checkstyle/checkstyle.xml")
}

// Quality gate ensures all checks pass
val qualityGate by tasks.registering {
    dependsOn(
        tasks.check,
        tasks.named("checkstyleMain"),
        tasks.named("detektMain"),
        tasks.named("pmdMain"),
        tasks.named("spotlessCheck"),
        tasks.named("javadoc2Check")
    )
}
```

### Build Optimization Strategy

#### 1. **Configuration on Demand**
```properties
org.gradle.configureondemand=true
```
- Only configures relevant projects, reduces configuration time by ~50%

#### 2. **Parallel Execution**
```properties
org.gradle.parallel=true
```
- Executes independent tasks concurrently, utilizes multi-core CPUs

#### 3. **Build Cache**
```kotlin
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, ".gradle/build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}
```
- Caches task outputs locally, reuses unchanged task results

#### 4. **Incremental Compilation**
```properties
kotlin.incremental=true
```
- Only recompiles changed files, significantly faster builds

## Quality Gates

### Multi-Layer Quality Enforcement

```
┌─────────────────────────────────────────────────────────────┐
│ Layer 1: Pre-Commit Hook (Fast Feedback)                    │
│ ├── Branch protection                                       │
│ ├── Staged file validation                                  │
│ ├── Commit message validation                               │
│ ├── Spotless check                                          │
│ ├── Detekt check                                            │
│ ├── Checkstyle check                                        │
│ ├── PMD check                                               │
│ ├── Unit tests (changed modules only)                       │
│ └── Coverage verification                                   │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│ Layer 2: Pre-Push Hook (Comprehensive)                      │
│ ├── Full test suite                                         │
│ ├── All quality checks                                      │
│ └── Commit history inspection                               │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│ Layer 3: CI/CD Pipeline (GitHub Actions)                    │
│ ├── Quality job (Spotless, Checkstyle, Detekt, PMD)        │
│ ├── Test job (JUnit 5 + JaCoCo)                            │
│ ├── Dependency check (OWASP)                               │
│ ├── Build job                                               │
│ └── Quality gate (aggregated status)                        │
└─────────────────────────────────────────────────────────────┘
```

### Quality Thresholds

| Metric | Threshold | Enforcement |
|--------|-----------|-------------|
| **Line Coverage** | 80% | JaCoCo fails build |
| **Branch Coverage** | 60% | JaCoCo fails build |
| **Method Coverage** | 70% | JaCoCo fails build |
| **Class Coverage** | 80% | JaCoCo fails build |
| **Checkstyle Errors** | 0 | Fails build |
| **Checkstyle Warnings** | 0 | Fails build |
| **Detekt Issues** | Max 10 | Fails build |
| **PMD Violations** | 0 | Fails build |
| **Spotless Issues** | 0 | Fails build |

### Coverage Exclusions

The following are excluded from coverage requirements:
- Application classes (`*Application*`)
- Configuration classes (`*Configuration*`, `*Config*`)
- DTOs (`*Dto*`, `*DTO*`, `*Request*`, `*Response*`)
- Exceptions (`*Exception*`, `*Error*`)
- Constants (`*Constants*`)
- Entities (`*Entity*`, `*Model*`)
- Builders (`*Builder*`)

## Dependency Management

### Version Catalog

All dependencies are managed centrally in `gradle/libs.versions.toml`:

```toml
[versions]
springBoot = "4.0.1"
junitBom = "6.0.2"
kotlin = "2.3.0"
detekt = "1.23.7"

[libraries]
springboot-starter-web = { group = "org.springframework.boot", name = "spring-boot-starter-web" }

[plugins]
springboot = { id = "org.springframework.boot", version.ref = "springBoot" }
```

### Platform BOMs

Four platform BOMs provide curated dependency sets, consumed via dependency substitution:

| Platform | Maven Coordinate | Purpose | Key Dependencies |
|----------|-----------------|---------|------------------|
| **springboot** | `com.starter.platforms:springboot-platform` | Spring Boot services | Spring Boot, JPA, PostgreSQL, Flyway, OpenAPI |
| **test** | `com.starter.platforms:test-platform` | Testing | JUnit 5 |
| **web** | `com.starter.platforms:web-platform` | Web clients | Ktor, Kotlinx Serialization |
| **android** | `com.starter.platforms:android-platform` | Android apps | AndroidX, Compose |

Dependency substitution in `apps/micro-services/settings.gradle.kts`:
```kotlin
includeBuild("../../platforms") {
    dependencySubstitution {
        substitute(module("com.starter.platforms:springboot-platform")).using(project(":springboot"))
        substitute(module("com.starter.platforms:test-platform")).using(project(":test"))
        substitute(module("com.starter.platforms:web-platform")).using(project(":web"))
        substitute(module("com.starter.platforms:android-platform")).using(project(":android"))
    }
}
```

### Dependency Flow

```
Platform BOMs (platforms/)                  Version Catalog (gradle/libs.versions.toml)
    ↓ (dependency constraints)                          ↓ (dependency coordinates)
Shared Libraries (shared/)                  Convention Plugins (build-logic/)
    ↓ (implementation)                                  ↓ (applies config)
Microservices (apps/micro-services/) ←──────────────────┘
```

## CI/CD Pipeline

### GitHub Actions Workflow

```yaml
jobs:
  quality:        # Parallel - Code quality checks
  test:           # Parallel - Unit tests
  dependency-check: # Parallel - Vulnerability scan
  build:          # Depends on quality + test
  quality-gate:   # Final verification
```

### Pipeline Stages

1. **Quality Stage** (Parallel) - Spotless, Checkstyle, Detekt, PMD + upload reports
2. **Test Stage** (Parallel) - Unit tests, coverage verification, report generation + upload artifacts
3. **Dependency Check Stage** (Parallel) - OWASP dependency scan + upload report
4. **Build Stage** (Depends on Quality + Test) - Full build with all checks
5. **Quality Gate Stage** (Final) - Aggregates all results, blocks merge on failure

### Caching Strategy

```yaml
- uses: actions/setup-java@v4
  with:
    java-version: '25'
    distribution: 'temurin'
    cache: gradle  # Caches Gradle dependencies
```

- **Gradle Cache**: Cached between runs
- **Build Cache**: Local 30-day retention (configured in `settings.gradle.kts`)
- **Dependency Cache**: Remote cache (optional, future enhancement)

## Performance Optimizations

### Build Performance

| Optimization | Impact | Configuration |
|--------------|--------|---------------|
| **Parallel Execution** | ~50% faster | `org.gradle.parallel=true` |
| **Build Cache** | ~30% faster | Local + remote cache |
| **Configuration on Demand** | ~40% faster | `org.gradle.configureondemand=true` |
| **Incremental Compilation** | ~20% faster | `kotlin.incremental=true` |
| **Gradle Daemon** | ~15% faster | `org.gradle.daemon=true` |

### Memory Configuration

```properties
org.gradle.jvmargs=-Xmx4g -XX:MaxMetaspaceSize=512m
```

- **Heap**: 4GB max
- **Metaspace**: 512MB max
- **Heap Dump**: On OOM error

### Test Optimization

```kotlin
tasks.withType<Test>().configureEach {
    retry {
        maxRetries = 1        # Retry flaky tests once
        maxFailures = 5       # Max 5 failures before stopping
    }
}
```

## Security

### Static Analysis Security

1. **PMD Security Rules** - SQL injection, XSS vulnerabilities, insecure cryptography, hardcoded passwords
2. **Checkstyle Security** - Security manager usage, serialization issues, reflection abuse
3. **Detekt Security** - Unsafe type casts, null pointer risks, resource leaks

### Dependency Security

```yaml
- name: Analyze dependencies
  run: ./gradlew dependencyCheckAnalyze
```

- **OWASP Dependency-Check**: Scans for known vulnerabilities
- **CVE Database**: Up-to-date vulnerability database
- **Reports**: HTML, XML, JSON formats

### Secrets Management

- **Never commit secrets** - Use environment variables
- **Git-ignored files** - .env files, local configurations

## Best Practices

### 1. **Branching Strategy**

```
main (protected)
  └── develop (integration branch)
       ├── feature/your-feature
       ├── fix/issue-description
       └── refactor/improvement
```

### 2. **Commit Convention**

```
feat(auth): add OAuth2 login
fix(cart): resolve NPE in empty cart
docs(readme): update setup instructions
```

### 3. **Code Review Process**

1. All checks must pass (CI)
2. At least one approval
3. No unresolved conversations
4. Squash and merge to `develop`

### 4. **Testing Strategy**

- **Unit Tests**: Fast, isolated, mocked dependencies
- **Integration Tests**: Real dependencies, slower
- **Coverage**: Minimum 80% line, 60% branch

### 5. **Documentation**

- **README.md**: Project overview, quick start
- **CONTRIBUTING.md**: Contribution guidelines
- **ARCHITECTURE.md**: This document
- **MONOREPO_IMPROVEMENTS.md**: Improvement tracking and implementation summary
- **Code Comments**: Complex logic only

## Troubleshooting

### Common Issues

#### Build Failures

```bash
# Clean and rebuild
./gradlew clean build --refresh-dependencies

# Clear caches
rm -rf .gradle/ build/
./gradlew build
```

#### Hook Issues

```bash
# Reinstall hooks
bash scripts/install-hooks.sh

# Verify
git config --get core.hooksPath
```

#### Test Failures

```bash
# Verbose output
./gradlew test --info --stacktrace

# Specific test
./gradlew test --tests "com.starter.services.user.UserServiceTest"
```

## Future Improvements

### Planned Enhancements

1. **Remote Build Cache** - Shared cache across team, faster CI builds
2. **Kubernetes Deployment** - Helm charts, Docker Compose
3. **Observability** - Distributed tracing, metrics aggregation, centralized logging
4. **Feature Flags** - Togglz integration, environment-based flags
5. **API Documentation** - OpenAPI 3.0, Swagger UI

## Conclusion

This architecture provides:

- ✅ **Scalability**: Easy to add new services via composite build structure
- ✅ **Maintainability**: Consistent build configuration via convention plugins
- ✅ **Quality**: Multi-layer quality gates (pre-commit, pre-push, CI)
- ✅ **Performance**: Optimized build times (2-3x faster)
- ✅ **Security**: Vulnerability scanning (OWASP) + static analysis
- ✅ **Developer Experience**: Fast feedback loops, auto-fix tooling

The monorepo structure with 8 composite builds, 8 custom plugins, and 4 platform BOMs offers the best of both worlds: centralized management with isolated builds.