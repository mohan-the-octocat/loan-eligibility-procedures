CREATE PROCEDURE sp_CalculateEligibility(
    IN customer_id INT,
    IN customer_income DECIMAL(10,2),
    IN collateral_value DECIMAL(10,2),
    IN credit_score INT
)
BEGIN
    -- Error handling
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error in eligibility calculation';
    END;

    -- Variable declarations
    DECLARE income_eligible BOOLEAN;
    DECLARE collateral_eligible BOOLEAN;
    DECLARE credit_eligible BOOLEAN;
    DECLARE debt_ratio DECIMAL(5,2);
    DECLARE done INT DEFAULT FALSE;
    
    -- Temporary table for tracking eligibility checks
    CREATE TEMPORARY TABLE IF NOT EXISTS temp_eligibility_checks (
        check_type VARCHAR(50),
        check_result BOOLEAN,
        check_timestamp TIMESTAMP
    );

    -- Cursor for historical income verification
    DECLARE income_cursor CURSOR FOR 
        SELECT monthly_income 
        FROM income 
        WHERE customer_id = customer_id 
        ORDER BY verification_date DESC
        LIMIT 3;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Start transaction
    START TRANSACTION;
    
    -- Basic eligibility checks
    CALL sp_CheckIncome(customer_income, @income_eligible);
    INSERT INTO temp_eligibility_checks VALUES ('INCOME', @income_eligible, NOW());
    
    CALL sp_CheckCollateral(collateral_value, @collateral_eligible);
    INSERT INTO temp_eligibility_checks VALUES ('COLLATERAL', @collateral_eligible, NOW());
    
    -- Calculate risk score using custom function
    SET @risk_score = (credit_score/800) * (customer_income/collateral_value);
    
    -- Process historical income using cursor
    OPEN income_cursor;
    SET @avg_income = 0;
    SET @count = 0;
    
    read_loop: LOOP
        FETCH income_cursor INTO @monthly_income;
        IF done THEN
            LEAVE read_loop;
        END IF;
        SET @avg_income = @avg_income + @monthly_income;
        SET @count = @count + 1;
    END LOOP;
    
    CLOSE income_cursor;
    SET @avg_income = @avg_income / @count;

    -- Dynamic SQL for flexible criteria checking
    SET @sql = CONCAT('SELECT max_ltv_ratio FROM loan_criteria WHERE criteria_id = ', 
                     (SELECT MAX(criteria_id) FROM loan_criteria));
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    -- Final eligibility determination
    INSERT INTO eligibility_status (
        customer_id,
        min_amount,
        max_amount,
        eligibility_status,
        reason_code
    )
    SELECT 
        customer_id,
        CASE WHEN @risk_score > 0.7 THEN customer_income * 0.3 ELSE customer_income * 0.2 END,
        CASE WHEN @risk_score > 0.7 THEN customer_income * 0.8 ELSE customer_income * 0.6 END,
        CASE WHEN @income_eligible AND @collateral_eligible AND @risk_score > 0.5 
             THEN 'APPROVED' ELSE 'REJECTED' END,
        CONCAT('RISK_SCORE:', CAST(@risk_score AS CHAR));

    -- Cleanup
    DROP TEMPORARY TABLE IF EXISTS temp_eligibility_checks;
    
    COMMIT;
END;