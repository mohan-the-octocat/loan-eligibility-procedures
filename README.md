# Loan Eligibility Microservice

This project contains a Java-based Spring Boot microservice designed to determine the eligibility of customers for automobile loans based on their income and collateral value.

## Project Structure

- **src/main/java/**: Contains Java classes for the microservice
  - **controller/**: REST controllers for handling API requests
  - **service/**: Service classes for business logic
  - **repository/**: Repository interfaces for data access
  - **model/**: Entity classes for database tables
- **src/main/resources/**: Configuration files
  - application.properties: Database and JPA properties
- **Dockerfile**: Dockerfile for containerizing the application

## API Endpoints

### Calculate Eligibility
- **URL**: `/api/calculate-eligibility`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "customerIncome": 50000.00,
    "collateralValue": 25000.00
  }
  ```
- **Response**:
  ```json
  {
    "eligible": true,
    "maxLoanAmount": 40000.00
  }
  ```

### Get Repayment Terms
- **URL**: `/api/repayment-terms/{loanAmount}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "termMonths": 60,
    "interestRate": 0.05,
    "monthlyPayment": 500.00
  }
  ```

## Docker Usage

### Build Docker Image
```sh
docker build -t loan-eligibility .
```

### Run Docker Container
```sh
docker run -p 8080:8080 loan-eligibility
```

## Sequence Diagrams

### Calculate Eligibility Sequence Diagram
```plaintext
Customer -> EligibilityController: POST /api/calculate-eligibility
EligibilityController -> EligibilityService: calculateEligibility(request)
EligibilityService -> IncomeRepository: findByCustomerId(customerId)
EligibilityService -> CollateralRepository: findByCustomerId(customerId)
EligibilityService -> LoanCriteriaRepository: findById(1)
EligibilityService -> EligibilityController: EligibilityResponse
EligibilityController -> Customer: EligibilityResponse
```

### Get Repayment Terms Sequence Diagram
```plaintext
Customer -> EligibilityController: GET /api/repayment-terms/{loanAmount}
EligibilityController -> EligibilityService: getRepaymentTerms(loanAmount)
EligibilityService -> RepaymentTermsRepository: findByLoanAmount(loanAmount)
EligibilityService -> EligibilityController: RepaymentTermsResponse
EligibilityController -> Customer: RepaymentTermsResponse
```

## Setup Instructions

1. Clone the repository:
```sh
git clone https://github.com/mohan-the-octocat/loan-eligibility-procedures.git
cd loan-eligibility-procedures
```

2. Build the application:
```sh
./mvnw clean package
```

3. Run the application:
```sh
java -jar target/loan-eligibility-0.0.1-SNAPSHOT.jar
```

## License

This project is licensed under the MIT License.
