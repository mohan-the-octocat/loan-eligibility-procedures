# Use a multi-stage build to create a lightweight Docker image

# Stage 1: Build the Spring Boot application
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/loan-eligibility-microservice.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "loan-eligibility-microservice.jar"]
