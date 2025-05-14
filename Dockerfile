# Multi-stage Dockerfile for FromZeroAPI

# 1. Build stage: compile the application
FROM maven:3.8.7-openjdk-17 AS builder
WORKDIR /app

# Copy only necessary files for Maven to download dependencies
COPY pom.xml .
# Copy source code
COPY src ./src

# Package the application without running tests (adjust -DskipTests as needed)
RUN mvn clean package -DskipTests

# 2. Runtime stage: run the application in a minimal image
FROM openjdk:17-slim

# Create a non-root user and group for security
RUN addgroup --system spring && adduser --system spring -G spring

# Prepare application directory and switch to non-root
WORKDIR /app
USER spring:spring

# Copy built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Allow configuration of the server port via the PORT environment variable (default: 8080)
ENV SERVER_PORT=${PORT:-8080}

# Expose the default port
EXPOSE 8080

# Run the application, binding to the configured port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$SERVER_PORT -jar app.jar"]
