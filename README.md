# Loan Eligibility Procedures

This project contains a set of stored procedures designed to determine the eligibility of customers for automobile loans based on their income and collateral value.

## Project Structure

- **procedures/**: Contains stored procedures
  - sp_CalculateEligibility.sql: Main eligibility calculation with risk assessment
  - sp_CheckCollateral.sql: Collateral validation
  - sp_CheckIncome.sql: Income verification
  - sp_GetLoanCriteria.sql: Loan criteria retrieval
  - sp_GetRepaymentTerms.sql: Payment terms calculation
- **tables/**: Database schema definitions
- **main.sql**: Entry point for execution

## Advanced Features

- Transaction Management
- Cursor-based Historical Data Analysis
- Temporary Tables for Calculation Tracking
- Dynamic SQL for Flexible Criteria
- Custom Risk Scoring Functions
- Comprehensive Error Handling

## Technical Details

### Error Handling
- SQLEXCEPTION handlers
- Transaction rollback mechanisms
- Custom error states and messages

### Performance Considerations
- Temporary tables for intermediate calculations
- Cursor optimization for historical data
- Transaction management for data consistency

## Setup Instructions

1. Database Setup:
```sql
source /path/to/tables/*.sql
source /path/to/procedures/*.sql
```

## Usage

Call the appropriate stored procedures to assess customer eligibility based on their income and collateral. The results will indicate the eligibility status, minimum and maximum loan amounts, and repayment terms.

## License

This project is licensed under the MIT License.