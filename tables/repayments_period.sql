-- This file defines the structure of the repayments_period table
CREATE TABLE repayments_period (
    period_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    loan_amount DECIMAL(10, 2) NOT NULL,
    term_months INT NOT NULL,
    interest_rate DECIMAL(5, 2) NOT NULL,
    monthly_payment DECIMAL(10, 2) NOT NULL,
    start_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);