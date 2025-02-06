CREATE PROCEDURE sp_GetRepaymentTerms(
    IN loan_amount DECIMAL(10,2)
)
BEGIN
    SELECT 
        term_months,
        interest_rate,
        loan_amount * (interest_rate/12 * POWER(1 + interest_rate/12, term_months)) / 
        (POWER(1 + interest_rate/12, term_months) - 1) as monthly_payment
    FROM repayment_terms
    WHERE min_amount <= loan_amount AND max_amount >= loan_amount;
END;