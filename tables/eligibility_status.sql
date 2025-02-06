-- This file defines the structure of the eligibility_status table
CREATE TABLE eligibility_status (
    status_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    min_amount DECIMAL(10, 2),
    max_amount DECIMAL(10, 2),
    eligibility_status VARCHAR(50),
    assessment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reason_code VARCHAR(50),
    approved_by VARCHAR(100),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);