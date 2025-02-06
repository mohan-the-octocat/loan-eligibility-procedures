package com.loan.eligibility.repository;

import com.loan.eligibility.model.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing collateral data.
 * This interface extends JpaRepository to provide CRUD operations for the Collateral entity.
 */
@Repository
public interface CollateralRepository extends JpaRepository<Collateral, Integer> {
    // Custom query methods can be defined here if needed
}
