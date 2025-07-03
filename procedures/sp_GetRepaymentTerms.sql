CREATE OR REPLACE PROCEDURE sp_GetRepaymentTerms(
    loan_amount NUMERIC
)
BEGIN
    SELECT 
        term_months,
        interest_rate,
        loan_amount * (interest_rate/12 * POW(1 + interest_rate/12, term_months)) / 
        (POW(1 + interest_rate/12, term_months) - 1) as monthly_payment
    FROM repayment_terms
    WHERE min_amount <= loan_amount AND max_amount >= loan_amount;
END;