-- Example usage of the loan eligibility procedures
DELIMITER //

BEGIN
    -- Declare variables for customer data
    SET @customer_income = 50000.00;
    SET @collateral_value = 25000.00;
    
    -- Calculate eligibility
    CALL sp_CalculateEligibility(@customer_income, @collateral_value);
    
    -- If eligible, get repayment terms
    IF @is_eligible THEN
        CALL sp_GetRepaymentTerms(@max_loan_amount);
    END IF;
END //

DELIMITER ;