# Use an OpenJDK base image
FROM openjdk:latest

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/loan-eligibility-0.0.1-SNAPSHOT.jar /app/loan-eligibility.jar

# Expose the application port
EXPOSE 8080

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app/loan-eligibility.jar"]
