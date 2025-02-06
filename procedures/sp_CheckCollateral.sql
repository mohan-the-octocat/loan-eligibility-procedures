CREATE PROCEDURE sp_CheckCollateral(
    IN collateral_value DECIMAL(10,2),
    OUT is_eligible BOOLEAN
)
BEGIN
    DECLARE min_collateral DECIMAL(10,2);
    
    SELECT collateral_threshold INTO min_collateral
    FROM loan_criteria
    WHERE criteria_id = 1;
    
    SET is_eligible = collateral_value >= min_collateral;
END;