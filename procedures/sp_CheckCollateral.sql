CREATE OR REPLACE PROCEDURE sp_CheckCollateral(
    collateral_value NUMERIC,
    OUT is_eligible BOOL
)
BEGIN
    DECLARE min_collateral NUMERIC;
    
    SELECT collateral_threshold INTO min_collateral
    FROM loan_criteria
    WHERE criteria_id = 1;
    
    SET is_eligible = collateral_value >= min_collateral;
END;