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

# Install MariaDB service
RUN apt-get update && \
    apt-get install -y mariadb-server && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Initialize MariaDB with the dbscripts
COPY tables /docker-entrypoint-initdb.d
RUN service mysql start && \
    sleep 30s && \
    mysql -u root -e "source /docker-entrypoint-initdb.d/init.sql"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "loan-eligibility-microservice.jar"]
