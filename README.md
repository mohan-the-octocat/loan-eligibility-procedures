# Loan Eligibility Microservice

This project contains a Java-based Spring Boot microservice designed to determine the eligibility of customers for automobile loans based on their income and collateral value.

## Project Structure

- **src/main/java/com/loan/eligibility/**: Contains Java classes for the microservice
  - **controller/**: REST controllers
  - **service/**: Business logic services
  - **repository/**: Data access repositories
  - **model/**: Entity classes
- **src/main/resources/**: Configuration files
- **src/test/java/com/loan/eligibility/**: Unit tests
- **Dockerfile**: Dockerfile for containerizing the application
- **docs/**: Documentation for business logic

## Building and Running the Microservice

### Prerequisites

- Java 11 or higher
- Docker (optional, for containerization)

### Building the Application

1. Clone the repository:
```bash
git clone https://github.com/mohan-the-octocat/loan-eligibility-procedures.git
cd loan-eligibility-procedures
```

2. Build the application using Maven:
```bash
./mvnw clean package
```

### Running the Application

1. Run the application locally:
```bash
java -jar target/loan-eligibility-0.0.1-SNAPSHOT.jar
```

2. Run the application using Docker:
```bash
docker build -t loan-eligibility .
docker run -p 8080:8080 loan-eligibility
```

## API Endpoints

### Calculate Eligibility

- **URL**: `/eligibility/calculate`
- **Method**: `POST`
- **Request Body**:
```json
{
  "customerId": 1,
  "customerIncome": 50000.00,
  "collateralValue": 25000.00,
  "creditScore": 700
}
```
- **Response**:
```json
{
  "isEligible": true,
  "minAmount": 10000.00,
  "maxAmount": 40000.00,
  "riskScore": 0.75
}
```

### Get Repayment Terms

- **URL**: `/eligibility/repayment-terms`
- **Method**: `GET`
- **Query Parameters**: `loanAmount`
- **Response**:
```json
{
  "termMonths": 60,
  "interestRate": 0.05,
  "monthlyPayment": 500.00
}
```

## License

This project is licensed under the MIT License.
