CREATE TABLE income (
    income_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    monthly_income DECIMAL(10, 2) NOT NULL,
    income_source VARCHAR(100) NOT NULL,
    verification_status VARCHAR(50),
    verification_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);