package com.loan.eligibility.repository;

import com.loan.eligibility.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing customer data.
 * This interface extends JpaRepository to provide CRUD operations for the Customer entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Custom query methods can be defined here if needed
}
