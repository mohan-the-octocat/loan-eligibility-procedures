CREATE OR REPLACE PROCEDURE sp_CheckIncome(
    customer_income NUMERIC,
    OUT is_eligible BOOL
)
BEGIN
    DECLARE min_income NUMERIC;
    
    SELECT income_threshold INTO min_income
    FROM loan_criteria
    WHERE criteria_id = 1;
    
    SET is_eligible = customer_income >= min_income;
END;