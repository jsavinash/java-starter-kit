#!/usr/bin/env python3
"""
Fix standalone Gradle projects by:
1. Copying original source files (with Lombok annotations) from parent pattern directories
2. Adding Lombok as a compile-time dependency to all build.gradle.kts files
3. Adding all missing dependencies (Mockito, Spring, Hibernate, H2, Jackson, etc.)
"""

import os
import re
import subprocess

patterns_dir = os.path.dirname(os.path.abspath(__file__))
os.chdir(patterns_dir)

standalone_dirs = sorted([d for d in os.listdir('.') if d.endswith('-standalone')])

print(f"Found {len(standalone_dirs)} standalone directories")

# Step 1: Copy original source files from parent pattern directories
print("\nStep 1: Restoring original source files...")
for sdir in standalone_dirs:
    base = sdir[:-len('-standalone')]
    src_dir = os.path.join(base, "src")
    if os.path.isdir(src_dir):
        dest = os.path.join(sdir, "src")
        subprocess.run(["cp", "-rf", src_dir, dest], capture_output=True)
        print(f"  Restored src to: {sdir}")

# Step 2: Add all dependencies to build files
print("\nStep 2: Adding dependencies to build files...")

# Common dependencies used across many projects
common_deps = {
    "mockito": 'testImplementation("org.mockito:mockito-core:5.17.0")',
    "h2": 'implementation("com.h2database:h2:2.3.232")',
    "jackson": 'implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")',
    "gson": 'implementation("com.google.code.gson:gson:2.12.1")',
    "guice": 'implementation("com.google.inject:guice:7.0.0")',
    "guava": 'implementation("com.google.guava:guava:33.4.8-jre")',
    "hibernate": 'implementation("org.hibernate.orm:hibernate-core:7.1.5.Final")',
    "spring-context": 'implementation("org.springframework:spring-context:6.2.6")',
    "spring-web": 'implementation("org.springframework:spring-web:6.2.6")',
    "spring-boot-starter-web": 'implementation("org.springframework.boot:spring-boot-starter-web:4.0.1")',
    "spring-boot-starter-data-jpa": 'implementation("org.springframework.boot:spring-boot-starter-data-jpa:4.0.1")',
    "spring-boot-starter-actuator": 'implementation("org.springframework.boot:spring-boot-starter-actuator:4.0.1")',
    "spring-boot-starter-thymeleaf": 'implementation("org.springframework.boot:spring-boot-starter-thymeleaf:4.0.1")',
    "spring-boot-starter-test": 'testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.1")',
    "spring-retry": 'implementation("org.springframework.retry:spring-retry:2.0.11")',
    "jakarta-persistence": 'implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")',
    "jakarta-servlet": 'compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")',
    "jakarta-inject": 'implementation("jakarta.inject:jakarta.inject-api:2.0.1")',
    "jakarta-ejb": 'implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")',
    "mongodb-driver-sync": 'implementation("org.mongodb:mongodb-driver-sync:5.4.0")',
    "mongodb-driver-legacy": 'implementation("org.mongodb:mongodb-driver-legacy:5.4.0")',
    "bson": 'implementation("org.mongodb:bson:5.4.0")',
    "commons-lang3": 'implementation("org.apache.commons:commons-lang3:3.18.0")',
    "joda-money": 'implementation("org.joda:joda-money:1.0.4")',
    "htmlunit": 'implementation("org.htmlunit:htmlunit:4.10.0")',
    "selenium": 'implementation("org.seleniumhq.selenium:selenium-java:4.27.0")',
    "awaitility": 'testImplementation("org.awaitility:awaitility:4.3.0")',
    "hamcrest": 'testImplementation("org.hamcrest:hamcrest:3.0")',
    "system-lambda": 'testImplementation("com.github.stefanbirkner:system-lambda:1.2.1")',
    "junit4-migration": 'testImplementation("org.junit.jupiter:junit-jupiter-migrationsupport:5.12.2")',
    "zk-framework": 'implementation("org.zkoss.zk:zk:10.0.0")',
}

# Map projects to their required dependencies
project_deps = {
    "anti-corruption-layer": ["spring-context", "spring-boot-starter-web", "spring-boot-starter-test"],
    "caching": ["bson", "mongodb-driver-legacy", "mockito"],
    "command-query-responsibility-segregation": ["hibernate", "h2", "jakarta-persistence"],
    "composite-view": ["jakarta-servlet", "mockito"],
    "dao-factory": ["h2", "gson"],
    "data-access-object": ["h2", "mockito"],
    "data-bus": ["mockito"],
    "decorator": ["mockito"],
    "dependency-injection": ["guice", "jakarta-inject"],
    "domain-model": ["joda-money", "h2"],
    "double-buffer": ["commons-lang3"],
    "dynamic-proxy": ["spring-web", "jackson"],
    "event-aggregator": ["mockito"],
    "event-based-asynchronous": ["awaitility"],
    "event-driven-architecture": ["mockito"],
    "execute-around": ["junit4-migration"],
    "fluent-interface": ["mockito"],
    "flux": ["mockito"],
    "front-controller": ["mockito"],
    "half-sync-half-async": ["mockito"],
    "health-check": ["spring-boot-starter-web", "spring-boot-starter-actuator", "spring-retry", "jakarta-persistence", "h2"],
    "hexagonal-architecture": ["guava", "guice", "mongodb-driver-sync", "gson"],
    "intercepting-filter": ["mockito"],
    "layered-architecture": ["jakarta-persistence", "h2"],
    "marker-interface": ["hamcrest"],
    "mediator": ["mockito"],
    "metadata-mapping": ["hibernate", "h2", "jakarta-persistence"],
    "microservices-idempotent-consumer": ["spring-boot-starter-web", "spring-boot-starter-data-jpa", "h2", "mockito"],
    "microservices-log-aggregation": ["spring-boot-starter-web", "mockito"],
    "model-view-controller": ["mockito"],
    "model-view-viewmodel": ["zk-framework"],
    "monolithic-architecture": ["spring-boot-starter-web", "spring-boot-starter-data-jpa", "h2"],
    "monostate": ["mockito"],
    "observer": ["mockito"],
    "optimistic-offline-lock": ["mockito"],
    "page-controller": ["spring-context", "jakarta-servlet"],
    "page-object": ["htmlunit"],
    "partial-response": ["mockito"],
    "poison-pill": ["mockito"],
    "producer-consumer": ["mockito"],
    "promise": ["mockito"],
    "prototype": [],  # Lombok SneakyThrows issue with Java 25
    "rate-limiting-pattern": ["mockito"],
    "repository": ["jakarta-persistence", "h2", "spring-boot-starter-data-jpa"],
    "retry": ["hamcrest"],
    "separated-interface": ["mockito"],
    "serialized-entity": ["h2"],
    "serialized-lob": ["h2"],
    "servant": ["mockito"],
    "server-session": ["mockito"],
    "service-layer": ["jakarta-persistence", "h2"],
    "service-to-worker": [],  # Depends on model-view-controller package
    "session-facade": ["jakarta-ejb", "mockito"],
    "single-table-inheritance": ["spring-boot-starter-data-jpa", "h2", "jakarta-persistence"],
    "specification": ["mockito"],
    "strategy": ["mockito"],
    "subclass-sandbox": ["system-lambda"],
    "table-module": ["h2"],
    "template-method": ["mockito"],
    "templateview": ["mockito"],
    "thread-specific-storage": ["awaitility"],
    "transaction-script": ["h2", "mockito"],
    "twin": ["mockito"],
    "type-object": ["gson"],
    "unit-of-work": ["mockito"],
    "virtual-proxy": ["hamcrest"],
    "visitor": ["mockito"],
}

lombok_deps = [
    'compileOnly("org.projectlombok:lombok:1.18.38")',
    'annotationProcessor("org.projectlombok:lombok:1.18.38")',
    'testCompileOnly("org.projectlombok:lombok:1.18.38")',
    'testAnnotationProcessor("org.projectlombok:lombok:1.18.38")',
]

for sdir in standalone_dirs:
    build_file = os.path.join(sdir, "build.gradle.kts")
    if not os.path.exists(build_file):
        continue
    
    with open(build_file, 'r') as f:
        content = f.read()
    
    original = content
    base = sdir[:-len('-standalone')]
    
    # Add dependencies block if missing
    if 'dependencies {' not in content:
        content = content.replace(
            "repositories {",
            "dependencies {\n}\n\nrepositories {"
        )
    
    # Add Lombok if missing
    if 'lombok' not in content:
        content = content.replace(
            "dependencies {",
            "dependencies {\n    " + "\n    ".join(lombok_deps)
        )
    
    # Add project-specific dependencies
    if base in project_deps:
        deps_to_add = project_deps[base]
        for dep_key in deps_to_add:
            if dep_key in common_deps:
                dep_line = common_deps[dep_key]
                if dep_line not in content:
                    # Add after the last dependency line
                    lines = content.split('\n')
                    last_dep_idx = -1
                    for i, line in enumerate(lines):
                        if 'implementation(' in line or 'compileOnly(' in line or 'testImplementation(' in line or 'annotationProcessor(' in line:
                            last_dep_idx = i
                    if last_dep_idx >= 0:
                        lines.insert(last_dep_idx + 1, f"    {dep_line}")
                        content = '\n'.join(lines)
    
    if content != original:
        with open(build_file, 'w') as f:
            f.write(content)
        print(f"  Updated: {build_file}")

# Step 3: Handle prototype-standalone Lombok SneakyThrows issue
# Lombok 1.18.38 doesn't support Java 25 class file format
# Fix: downgrade Java version or upgrade Lombok
print("\nStep 2.5: Fixing prototype-standalone for Java 25 + Lombok compatibility...")
proto_build = "prototype-standalone/build.gradle.kts"
if os.path.exists(proto_build):
    with open(proto_build, 'r') as f:
        content = f.read()
    # Add JVM args to work around SneakyThrows issue
    if 'jvmArgs' not in content:
        content = content.replace(
            "tasks.named<Test>(\"test\") {",
            "tasks.named<Test>(\"test\") {\n    jvmArgs(\"--add-opens\", \"java.base/java.lang=ALL-UNNAMED\")"
        )
        with open(proto_build, 'w') as f:
            f.write(content)
        print(f"  Fixed: {proto_build}")

# Step 4: Rebuild all
print("\nStep 3: Rebuilding all standalone projects...\n")

success = 0
failed = 0
failed_list = []
for sdir in standalone_dirs:
    gradlew = os.path.join(sdir, "gradlew")
    if not os.path.exists(gradlew):
        continue
    result = subprocess.run(
        ["./gradlew", "build", "--quiet"],
        cwd=os.path.abspath(sdir),
        capture_output=True,
        text=True,
        timeout=120
    )
    if result.returncode == 0:
        success += 1
    else:
        failed += 1
        failed_list.append(sdir)
        errors = [l for l in result.stderr.split('\n') if 'error:' in l]
        if errors:
            print(f"  FAILED: {sdir} - {errors[0][:150]}")

print(f"\nResults: {success} SUCCESS, {failed} FAILED out of {len(standalone_dirs)} total")
if failed_list:
    print(f"\nFailed projects ({len(failed_list)}):")
    for d in failed_list:
        print(f"  - {d}")