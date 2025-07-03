CREATE OR REPLACE PROCEDURE sp_GetLoanCriteria()
BEGIN
    SELECT 
        criteria_id,
        income_threshold,
        collateral_threshold
    FROM 
        loan_criteria;
END;