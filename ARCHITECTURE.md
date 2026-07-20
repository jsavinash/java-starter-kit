# Java Starter Kit - Architecture Documentation

## Overview

This document provides a deep dive into the architecture and design decisions of the Java Starter Kit monorepo.

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
java-starter-kit-final/
├── Composite Builds (isolated Gradle projects)
│   ├── apps/micro-services/     # 17 Spring Boot microservices
│   ├── shared/                  # 5 shared libraries
│   └── aggregation/             # Report aggregation
│
├── Build Logic (convention plugins)
│   └── build-logic/             # Reusable Gradle plugins
│
├── Platforms (BOMs)
│   └── platforms/               # Dependency management
│
└── Root Project
    └── (orchestration & configuration)
```

### Composite Build Strategy

The monorepo uses **Gradle Composite Builds** to achieve isolation while maintaining unified management:

#### 1. **apps/micro-services** (Composite Build)
- **Purpose**: Contains all microservices
- **Benefits**:
  - Independent versioning per service
  - Isolated dependency resolution
  - Parallel build execution
  - Independent testing
- **Structure**:
  ```
  apps/micro-services/
  ├── settings.gradle.kts          # Includes all services
  ├── build.gradle.kts             # Aggregation tasks
  ├── api-gateway/
  ├── user/
  ├── order/
  └── ...
  ```

#### 2. **shared** (Composite Build)
- **Purpose**: Shared libraries consumed by microservices
- **Benefits**:
  - Versioned independently
  - Published to local Maven repository
  - Reusable across services
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

#### 3. **aggregation** (Composite Build)
- **Purpose**: Aggregate reports from all modules
- **Benefits**:
  - Unified coverage reports
  - Cross-module analysis
  - Centralized reporting

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

The project uses **Gradle Convention Plugins** to enforce consistency:

#### Plugin Hierarchy

```
com.custom-plugins.combined (base)
├── com.custom-plugins.java-library
├── com.custom-plugins.java-app
└── com.custom-plugins.springboot-app
```

#### Plugin Responsibilities

| Plugin | Purpose | Applied To |
|--------|---------|-----------|
| `combined` | Base quality checks (Spotless, Detekt, Checkstyle, PMD, JaCoCo) | All modules |
| `java-library` | Java library configuration | Shared libraries |
| `java-app` | Java application configuration | CLI apps |
| `springboot-app` | Spring Boot + all quality checks | Microservices |

### Plugin Implementation Pattern

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
}

// Quality gate ensures all checks pass
val qualityGate by tasks.registering {
    dependsOn(
        tasks.check,
        tasks.named("checkstyleMain"),
        tasks.named("detektMain"),
        tasks.named("pmdMain"),
        tasks.named("spotlessCheck")
    )
}
```

### Build Optimization Strategy

#### 1. **Configuration on Demand**
```properties
org.gradle.configureondemand=true
```
- Only configures relevant projects
- Reduces configuration time by ~50%

#### 2. **Parallel Execution**
```properties
org.gradle.parallel=true
```
- Executes independent tasks concurrently
- Utilizes multi-core CPUs

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
- Caches task outputs locally
- Reuses unchanged task results

#### 4. **Incremental Compilation**
```properties
kotlin.incremental=true
```
- Only recompiles changed files
- Significantly faster builds

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

Four platform BOMs provide curated dependency sets:

| Platform | Purpose | Key Dependencies |
|----------|---------|------------------|
| **springboot** | Spring Boot services | Spring Boot, JPA, PostgreSQL, Flyway, OpenAPI |
| **test** | Testing | JUnit 5 |
| **web** | Web clients | Ktor, Kotlinx Serialization |
| **android** | Android apps | AndroidX, Compose |

### Dependency Flow

```
Platform BOMs (platforms/)
    ↓ (constraints)
Shared Libraries (shared/)
    ↓ (implementation)
Microservices (apps/micro-services/)
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

1. **Quality Stage** (Parallel)
   - Spotless check
   - Checkstyle analysis
   - Detekt analysis
   - PMD analysis
   - Upload reports

2. **Test Stage** (Parallel)
   - Unit tests
   - Coverage verification
   - Report generation
   - Upload artifacts

3. **Dependency Check Stage** (Parallel)
   - OWASP dependency scan
   - Vulnerability report
   - Upload report

4. **Build Stage** (Depends on Quality + Test)
   - Full build
   - All checks pass
   - Upload build artifacts

5. **Quality Gate Stage** (Final)
   - Aggregates all results
   - Fails if any job failed
   - Blocks merge

### Caching Strategy

```yaml
- uses: actions/setup-java@v4
  with:
    cache: gradle  # Caches Gradle dependencies
```

- **Gradle Cache**: Cached between runs
- **Build Cache**: Local 30-day retention
- **Dependency Cache**: Remote cache (optional)

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

1. **PMD Security Rules**
   - SQL injection
   - XSS vulnerabilities
   - Insecure cryptography
   - Hardcoded passwords

2. **Checkstyle Security**
   - Security manager usage
   - Serialization issues
   - Reflection abuse

3. **Detekt Security**
   - Unsafe type casts
   - Null pointer risks
   - Resource leaks

### Dependency Security

```yaml
- name: Analyze dependencies
  run: ./gradlew dependencyCheckAnalyze
```

- **OWASP Dependency-Check**: Scans for known vulnerabilities
- **CVE Database**: Up-to-date vulnerability database
- **Reports**: HTML, XML, JSON formats

### Secrets Management

```bash
# .env file (git-ignored)
DB_PASSWORD=secret
API_KEY=secret
```

- **Never commit secrets**
- **Use environment variables**
- **Git-ignored .env files**

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
fix(cart): resolve NPE
docs(readme): update setup
```

### 3. **Code Review Process**

1. All checks must pass (CI)
2. At least one approval
3. No unresolved conversations
4. Squash and merge

### 4. **Testing Strategy**

- **Unit Tests**: Fast, isolated, mocked
- **Integration Tests**: Real dependencies, slower
- **Coverage**: Minimum 80% line, 60% branch

### 5. **Documentation**

- **README**: Project overview, quick start
- **CONTRIBUTING**: Contribution guidelines
- **ARCHITECTURE**: This document
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
./gradlew test --tests "com.example.Test"
```

## Future Improvements

### Planned Enhancements

1. **Remote Build Cache**
   - Shared cache across team
   - Faster CI builds

2. **Kubernetes Deployment**
   - Helm charts
   - Docker Compose

3. **Observability**
   - Distributed tracing
   - Metrics aggregation
   - Centralized logging

4. **Feature Flags**
   - Togglz integration
   - Environment-based flags

5. **API Documentation**
   - OpenAPI 3.0
   - Swagger UI

## Conclusion

This architecture provides:

- ✅ **Scalability**: Easy to add new services
- ✅ **Maintainability**: Consistent build configuration
- ✅ **Quality**: Multi-layer quality gates
- ✅ **Performance**: Optimized build times
- ✅ **Security**: Vulnerability scanning
- ✅ **Developer Experience**: Fast feedback loops

The monorepo structure with composite builds offers the best of both worlds: centralized management with isolated builds.