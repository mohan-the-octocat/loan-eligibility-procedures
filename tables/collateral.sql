CREATE TABLE collateral (
    collateral_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    vehicle_make VARCHAR(50) NOT NULL,
    vehicle_model VARCHAR(50) NOT NULL,
    vehicle_year INT NOT NULL,
    vehicle_value DECIMAL(10, 2) NOT NULL,
    appraisal_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);