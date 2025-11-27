#!/bin/bash
set -e

echo "Starting deployment..."

# Build the application
echo "Building application..."
./mvnw clean package -DskipTests

echo "Application built successfully!"

# Start the application
echo "Starting application..."
java -Dspring.profiles.active=prod -jar target/*.jar