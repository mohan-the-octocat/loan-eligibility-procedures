CREATE OR REPLACE PROCEDURE sp_CalculateEligibility(
    customer_id INT64,
    customer_income NUMERIC,
    collateral_value NUMERIC,
    credit_score INT64
)
BEGIN
    -- Variable declarations
    DECLARE income_eligible BOOL;
    DECLARE collateral_eligible BOOL;
    DECLARE risk_score NUMERIC;
    DECLARE avg_income NUMERIC;

    -- Basic eligibility checks
    CALL sp_CheckIncome(customer_income, income_eligible);
    CALL sp_CheckCollateral(collateral_value, collateral_eligible);
    
    -- Calculate risk score
    SET risk_score = (credit_score / 800) * (customer_income / collateral_value);
    
    -- Process historical income
    SET avg_income = (
        SELECT AVG(monthly_income)
        FROM income
        WHERE income.customer_id = sp_CalculateEligibility.customer_id
    );

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
        CASE WHEN risk_score > 0.7 THEN customer_income * 0.3 ELSE customer_income * 0.2 END,
        CASE WHEN risk_score > 0.7 THEN customer_income * 0.8 ELSE customer_income * 0.6 END,
        CASE WHEN income_eligible AND collateral_eligible AND risk_score > 0.5 
             THEN 'APPROVED' ELSE 'REJECTED' END,
        'RISK_SCORE:' || CAST(risk_score AS STRING);

END;