CREATE TABLE loan_criteria (
    criteria_id INT PRIMARY KEY AUTO_INCREMENT,
    income_threshold DECIMAL(10, 2) NOT NULL,
    collateral_threshold DECIMAL(10, 2) NOT NULL,
    min_credit_score INT NOT NULL,
    max_ltv_ratio DECIMAL(5, 2) NOT NULL,
    min_income_ratio DECIMAL(5, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);