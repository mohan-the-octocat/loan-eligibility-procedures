# Use openjdk:11-jre-slim as the base image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/loan-eligibility-0.0.1-SNAPSHOT.jar /app/loan-eligibility.jar

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/loan-eligibility.jar"]
