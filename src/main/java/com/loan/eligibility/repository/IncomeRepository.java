package com.loan.eligibility.repository;

import com.loan.eligibility.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing income data.
 * This interface extends JpaRepository to provide CRUD operations for the Income entity.
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    // Custom query methods can be defined here if needed
}
