#!/bin/bash

# Find all build.gradle.kts files in educational-resources and fix missing libs aliases

for file in educational-resources/system-design/patterns/*/build.gradle.kts; do
    if [ -f "$file" ]; then
        echo "Processing: $file"
        
        # Replace libs.hibernate.core with string
        sed -i '' 's/libs\.hibernate\.core/"org.hibernate.orm:hibernate-core:7.1.5.Final"/g' "$file"
        
        # Replace libs.h2 with string
        sed -i '' 's/libs\.h2/"com.h2database:h2:2.3.232"/g' "$file"
        
        # Replace libs.jakarta.persistence.api with string
        sed -i '' 's/libs\.jakarta\.persistence\.api/"jakarta.persistence:jakarta.persistence-api:3.2.0"/g' "$file"
        
        # Replace libs.jakarta.servlet.api with string
        sed -i '' 's/libs\.jakarta\.servlet\.api/"jakarta.servlet:jakarta.servlet-api:6.1.0"/g' "$file"
        
        # Replace libs.bson with string
        sed -i '' 's/libs\.bson/"org.mongodb:bson:5.4.0"/g' "$file"
        
        # Replace libs.mongodb.driver.legacy with string
        sed -i '' 's/libs\.mongodb\.driver\.legacy/"org.mongodb:mongo-java-driver:5.4.0"/g' "$file"
        
        # Replace libs.mongodb.driver.sync with string
        sed -i '' 's/libs\.mongodb\.driver\.sync/"org.mongodb:mongodb-driver-sync:5.4.0"/g' "$file"
        
        # Replace libs.gson with string
        sed -i '' 's/libs\.gson/"com.google.code.gson:gson:2.12.1"/g' "$file"
        
        echo "  Fixed: $file"
    fi
done

echo "Done fixing all educational-resources build files"