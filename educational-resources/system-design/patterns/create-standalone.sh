#!/bin/bash
# Script to create standalone Gradle projects for all design patterns
# Usage: cd educational-resources/system-design/patterns && bash create-standalone.sh

set -e

# Get all pattern directories (excluding standalone ones, build, .gradle, .mvn)
PATTERNS=$(ls -d */ 2>/dev/null | grep -v "standalone" | grep -v "\.gradle" | grep -v "\.mvn" | grep -v "build/" | sed 's/\///')

echo "Found $(echo "$PATTERNS" | wc -l) pattern directories"

for pattern in $PATTERNS; do
  # Skip if no build file
  if [ ! -f "$pattern/build.gradle.kts" ] && [ ! -f "$pattern/pom.xml" ]; then
    continue
  fi
  
  STANDALONE_DIR="${pattern}-standalone"
  
  # Skip if already exists
  if [ -d "$STANDALONE_DIR" ]; then
    echo "Skipping $pattern (standalone already exists)"
    continue
  fi
  
  echo "Processing $pattern..."
  
  # Create standalone directory
  mkdir -p "$STANDALONE_DIR"
  
  # Copy source, etc, README, gitignore
  cp -r "$pattern/src" "$STANDALONE_DIR/" 2>/dev/null || true
  cp -r "$pattern/etc" "$STANDALONE_DIR/" 2>/dev/null || true
  cp "$pattern/README.md" "$STANDALONE_DIR/" 2>/dev/null || true
  cp "$pattern/.gitignore" "$STANDALONE_DIR/" 2>/dev/null || true
  cp "$pattern/docker-compose.yml" "$STANDALONE_DIR/" 2>/dev/null || true
  
  # Create settings.gradle.kts
  echo 'rootProject.name = "'"$pattern"'"' > "$STANDALONE_DIR/settings.gradle.kts"
  
  # Extract main class from build file
  MAIN_CLASS=""
  if [ -f "$pattern/build.gradle.kts" ]; then
    MAIN_CLASS=$(grep "mainClass" "$pattern/build.gradle.kts" 2>/dev/null | sed 's/.*set("//;s/").*//' || echo "")
  elif [ -f "$pattern/pom.xml" ]; then
    MAIN_CLASS=$(grep -A1 "mainClass" "$pattern/pom.xml" 2>/dev/null | grep "<mainClass>" | sed 's/.*<mainClass>//;s/<\/mainClass>.*//' || echo "")
  fi
  
  # Extract dependencies from build file
  EXTRA_DEPS=""
  TEST_DEPS=""
  
  if [ -f "$pattern/build.gradle.kts" ]; then
    # Gradle project
    EXTRA_DEPS=$(grep -E "implementation\(.*\)" "$pattern/build.gradle.kts" 2>/dev/null | grep -v "slf4j" | grep -v "logback" | grep -v "junit" | grep -v "platform" | sed 's/^[[:space:]]*//' || true)
    TEST_DEPS=$(grep -E "testImplementation\(.*\)" "$pattern/build.gradle.kts" 2>/dev/null | grep -v "junit" | grep -v "platform" || true)
  elif [ -f "$pattern/pom.xml" ]; then
    # Maven project - extract common dependencies
    if grep -q "lombok" "$pattern/pom.xml" 2>/dev/null; then
      # Has Lombok, will need manual replacement
      :
    fi
    if grep -q "mongodb" "$pattern/pom.xml" 2>/dev/null; then
      EXTRA_DEPS="implementation(\"org.mongodb:mongodb-driver-legacy:5.4.0\")"
    fi
  fi
  
  # Create build.gradle.kts
  cat > "$STANDALONE_DIR/build.gradle.kts" << 'BUILDEOF'
plugins {
    id("java")
    id("application")
}

group = "com.iluwatar"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

BUILDEOF
  
  # Add main class if found
  if [ -n "$MAIN_CLASS" ]; then
    echo "application {" >> "$STANDALONE_DIR/build.gradle.kts"
    echo "    mainClass.set(\"$MAIN_CLASS\")" >> "$STANDALONE_DIR/build.gradle.kts"
    echo "}" >> "$STANDALONE_DIR/build.gradle.kts"
  fi
  
  # Add repositories
  cat >> "$STANDALONE_DIR/build.gradle.kts" << 'REPOEOF'

repositories {
    mavenCentral()
}

REPOEOF
  
  # Add dependencies
  echo "dependencies {" >> "$STANDALONE_DIR/build.gradle.kts"
  echo "    implementation(\"org.slf4j:slf4j-api:2.0.18\")" >> "$STANDALONE_DIR/build.gradle.kts"
  echo "    implementation(\"ch.qos.logback:logback-classic:1.5.34\")" >> "$STANDALONE_DIR/build.gradle.kts"
  
  # Add extra dependencies
  if [ -n "$EXTRA_DEPS" ]; then
    echo "$EXTRA_DEPS" >> "$STANDALONE_DIR/build.gradle.kts"
  fi
  
  # Add test dependencies
  echo "" >> "$STANDALONE_DIR/build.gradle.kts"
  echo "    testImplementation(platform(\"org.junit:junit-bom:6.0.2\"))" >> "$STANDALONE_DIR/build.gradle.kts"
  echo "    testImplementation(\"org.junit.jupiter:junit-jupiter\")" >> "$STANDALONE_DIR/build.gradle.kts"
  echo "    testRuntimeOnly(\"org.junit.platform:junit-platform-launcher\")" >> "$STANDALONE_DIR/build.gradle.kts"
  
  if [ -n "$TEST_DEPS" ]; then
    echo "$TEST_DEPS" >> "$STANDALONE_DIR/build.gradle.kts"
  fi
  
  echo "}" >> "$STANDALONE_DIR/build.gradle.kts"
  
  # Add test and run config
  cat >> "$STANDALONE_DIR/build.gradle.kts" << 'TASKEOF'

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
TASKEOF
  
  # Remove Lombok imports and annotations from all Java files
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^import lombok/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Slf4j/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Data/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@AllArgsConstructor/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@ToString/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@EqualsAndHashCode/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Getter/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Setter/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Builder/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@NoArgsConstructor/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@RequiredArgsConstructor/d' {} \; 2>/dev/null || true
  find "$STANDALONE_DIR/src" -name "*.java" -exec sed -i '' '/^@Value/d' {} \; 2>/dev/null || true
  
  echo "Created $STANDALONE_DIR"
done

echo ""
echo "All standalone projects created. Now generating Gradle wrappers..."

# Generate Gradle wrappers for all standalone projects
for dir in *-standalone/; do
  if [ -f "${dir}build.gradle.kts" ] && [ ! -f "${dir}gradlew" ]; then
    echo "Generating wrapper for $dir"
    cd "$dir"
    gradle wrapper --gradle-version 9.6.1 2>&1 | tail -1
    cd ..
  fi
done

echo ""
echo "Done! All standalone projects created with Gradle 9.6.1 wrappers."