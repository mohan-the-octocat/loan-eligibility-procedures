# Business Logic for Loan Eligibility

This document provides an overview of the business logic used in the loan eligibility microservice. The microservice determines the eligibility of customers for automobile loans based on their income, collateral value, and credit score.

## Income Eligibility

The income eligibility check ensures that the customer's monthly income meets the minimum threshold required for loan approval. The logic for this check is as follows:

1. Retrieve the customer's monthly income from the `income` table.
2. Compare the total monthly income with the `income_threshold` value from the `loan_criteria` table.
3. If the total monthly income is greater than or equal to the `income_threshold`, the customer is considered income eligible.

## Collateral Eligibility

The collateral eligibility check ensures that the value of the customer's collateral meets the minimum threshold required for loan approval. The logic for this check is as follows:

1. Retrieve the value of the customer's collateral from the `collateral` table.
2. Compare the total collateral value with the `collateral_threshold` value from the `loan_criteria` table.
3. If the total collateral value is greater than or equal to the `collateral_threshold`, the customer is considered collateral eligible.

## Risk Score Calculation

The risk score calculation is used to assess the overall risk associated with approving a loan for a customer. The risk score is calculated using the customer's credit score, income, and collateral value. The logic for this calculation is as follows:

1. Retrieve the customer's credit score from the `customer` table.
2. Calculate the total monthly income from the `income` table.
3. Calculate the total collateral value from the `collateral` table.
4. Compute the risk score using the formula: `(credit_score / 800) * (total_income / total_collateral_value)`.
5. If the risk score is greater than 0.5, the customer is considered to have an acceptable risk level.

## Eligibility Determination

The final eligibility determination is based on the results of the income eligibility, collateral eligibility, and risk score calculations. The logic for this determination is as follows:

1. Check if the customer is income eligible.
2. Check if the customer is collateral eligible.
3. Calculate the risk score.
4. If the customer is income eligible, collateral eligible, and has a risk score greater than 0.5, the customer is considered eligible for a loan.
5. Determine the minimum and maximum loan amounts based on the customer's total monthly income and risk score.

## Repayment Terms

The repayment terms calculation provides the details of the loan repayment schedule, including the term length, interest rate, and monthly payment amount. The logic for this calculation is as follows:

1. Retrieve the loan amount from the request.
2. Retrieve the repayment terms from the `repayment_terms` table based on the loan amount.
3. Calculate the monthly payment using the formula: `loan_amount * (interest_rate / 12 * POWER(1 + interest_rate / 12, term_months)) / (POWER(1 + interest_rate / 12, term_months) - 1)`.
4. Return the term length, interest rate, and monthly payment amount.

## Summary

The loan eligibility microservice uses a combination of income eligibility, collateral eligibility, and risk score calculations to determine the eligibility of customers for automobile loans. The repayment terms calculation provides the details of the loan repayment schedule. This business logic ensures that only customers who meet the required criteria are approved for loans, reducing the risk for the lender.
