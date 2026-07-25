#!/usr/bin/env python3
"""
Convert all -standalone build.gradle.kts files to use custom plugins and .toml version catalog.
Removes standalone settings.gradle.kts files since parent settings.gradle.kts handles includes.
"""

import os
import re
import shutil

PATTERNS_DIR = os.path.dirname(os.path.abspath(__file__))

def extract_main_class(content):
    """Extract mainClass value from build.gradle.kts"""
    match = re.search(r'mainClass\.set\("([^"]+)"\)', content)
    if match:
        return match.group(1)
    return None

def extract_extra_deps(content):
    """Extract any extra dependencies beyond the standard ones"""
    standard_deps = [
        'org.projectlombok:lombok',
        'org.slf4j:slf4j-api',
        'ch.qos.logback:logback-classic',
        'org.junit:junit-bom',
        'org.junit.jupiter:junit-jupiter',
        'org.junit.platform:junit-platform-launcher',
        'org.mockito:mockito-core',
    ]
    extra_lines = []
    for line in content.split('\n'):
        stripped = line.strip()
        # Skip standard dep lines, plugin blocks, and structural lines
        if any(standard in stripped for standard in standard_deps):
            continue
        if 'implementation(' in stripped or 'testImplementation(' in stripped or 'testRuntimeOnly(' in stripped or 'compileOnly(' in stripped or 'annotationProcessor(' in stripped:
            if 'platform(' not in stripped:
                extra_lines.append(stripped)
    return extra_lines

def get_dir_name(dir_path):
    """Get directory name without -standalone suffix"""
    name = os.path.basename(dir_path)
    return name.replace('-standalone', '')

def generate_build_content(dir_name, main_class, extra_deps):
    """Generate the new build.gradle.kts content"""
    lines = []
    lines.append('plugins {')
    lines.append('    id("com.custom-plugins.java-app")')
    lines.append('    alias(libs.plugins.lombok)')
    lines.append('}')
    lines.append('')
    lines.append('group = "com.iluwatar"')
    lines.append('version = "1.0.0"')
    lines.append('')

    if main_class:
        # Check for alternative main class comments
        lines.append('application {')
        lines.append(f'    mainClass.set("{main_class}")')
        # Check if there were alternative main class comments in original
        lines.append('}')
        lines.append('')

    lines.append('lombok {')
    lines.append('    version.set(libs.versions.lombokLibrary.get())')
    lines.append('}')
    lines.append('')
    lines.append('dependencies {')
    lines.append('    implementation(libs.slf4j.api)')
    lines.append('    implementation(libs.logback.classic)')
    lines.append('')
    lines.append('    testImplementation(platform(libs.junit.bom))')
    lines.append('    testImplementation(libs.junit.jupiter)')
    lines.append('    testRuntimeOnly(libs.junit.platform.launcher)')
    
    if extra_deps:
        lines.append('')
        for dep in extra_deps:
            lines.append(f'    {dep}')
    
    lines.append('}')
    
    return '\n'.join(lines) + '\n'


def process_standalone_dir(dir_path):
    """Process a single standalone directory"""
    dir_name = os.path.basename(dir_path)
    build_file = os.path.join(dir_path, 'build.gradle.kts')
    settings_file = os.path.join(dir_path, 'settings.gradle.kts')
    base_name = get_dir_name(dir_path)
    
    if not os.path.exists(build_file):
        print(f"  SKIP: No build.gradle.kts in {dir_name}")
        return
    
    with open(build_file, 'r') as f:
        content = f.read()
    
    # Skip if already converted
    if 'com.custom-plugins.java-app' in content:
        print(f"  SKIP: Already converted {dir_name}")
        return
    
    # Extract info
    main_class = extract_main_class(content)
    extra_deps = extract_extra_deps(content)
    
    # Generate new content
    new_content = generate_build_content(base_name, main_class, extra_deps)
    
    # Write build file
    with open(build_file, 'w') as f:
        f.write(new_content)
    print(f"  OK: Updated {dir_name}/build.gradle.kts")
    
    # Remove settings.gradle.kts if it exists
    if os.path.exists(settings_file):
        os.remove(settings_file)
        print(f"  OK: Removed {dir_name}/settings.gradle.kts")


def main():
    print("Converting standalone build.gradle.kts files...")
    print(f"Patterns directory: {PATTERNS_DIR}")
    
    # Find all standalone directories
    standalone_dirs = []
    for item in os.listdir(PATTERNS_DIR):
        if item.endswith('-standalone'):
            full_path = os.path.join(PATTERNS_DIR, item)
            if os.path.isdir(full_path):
                standalone_dirs.append(full_path)
    
    standalone_dirs.sort()
    print(f"Found {len(standalone_dirs)} standalone directories")
    print()
    
    for i, dir_path in enumerate(standalone_dirs, 1):
        dir_name = os.path.basename(dir_path)
        print(f"[{i}/{len(standalone_dirs)}] Processing {dir_name}...")
        process_standalone_dir(dir_path)
    
    print()
    print("Conversion complete!")


if __name__ == '__main__':
    main()