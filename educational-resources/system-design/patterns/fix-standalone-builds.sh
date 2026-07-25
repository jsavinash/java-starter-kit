#!/bin/bash
# Fix standalone builds by adding Lombok dependency to all build.gradle.kts files
# Run from: educational-resources/system-design/patterns
# Usage: bash fix-standalone-builds.sh

set -e

echo "Adding Lombok dependency to all standalone build.gradle.kts files..."

for dir in *-standalone/; do
  build_file="${dir}build.gradle.kts"
  if [ -f "$build_file" ]; then
    # Check if Lombok is already added
    if ! grep -q "lombok" "$build_file" 2>/dev/null; then
      # Add Lombok to dependencies section
      sed -i '' '/^dependencies {/a\
    compileOnly("org.projectlombok:lombok:1.18.38")\
    annotationProcessor("org.projectlombok:lombok:1.18.38")\
    testCompileOnly("org.projectlombok:lombok:1.18.38")\
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")\
' "$build_file"
      echo "  Added Lombok to: $build_file"
    fi
  fi
done

echo ""
echo "Done! Lombok added to all standalone build files."
echo "Now rebuilding all standalone projects..."
echo ""

# Rebuild all
success=0
failed=0
for dir in *-standalone/; do
  if [ -f "${dir}gradlew" ]; then
    echo "Building $dir..."
    cd "$dir"
    if ./gradlew build --quiet 2>/dev/null; then
      success=$((success + 1))
    else
      failed=$((failed + 1))
      echo "  FAILED: $dir"
    fi
    cd ..
  fi
done

echo ""
echo "Results: $success SUCCESS, $failed FAILED"