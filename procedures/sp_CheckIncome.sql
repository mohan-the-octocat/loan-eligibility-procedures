CREATE PROCEDURE sp_CheckIncome(
    IN customer_income DECIMAL(10,2),
    OUT is_eligible BOOLEAN
)
BEGIN
    DECLARE min_income DECIMAL(10,2);
    
    SELECT income_threshold INTO min_income
    FROM loan_criteria
    WHERE criteria_id = 1;
    
    SET is_eligible = customer_income >= min_income;
END;