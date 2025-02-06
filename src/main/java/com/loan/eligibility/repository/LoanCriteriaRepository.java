package com.loan.eligibility.repository;

import com.loan.eligibility.model.LoanCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing loan criteria data.
 * This interface extends JpaRepository to provide CRUD operations for the LoanCriteria entity.
 */
@Repository
public interface LoanCriteriaRepository extends JpaRepository<LoanCriteria, Integer> {

    /**
     * Finds the minimum income threshold from the loan criteria.
     *
     * @return the minimum income threshold
     */
    @Query("SELECT lc.incomeThreshold FROM LoanCriteria lc WHERE lc.criteriaId = 1")
    double findMinIncomeThreshold();

    /**
     * Finds the minimum collateral threshold from the loan criteria.
     *
     * @return the minimum collateral threshold
     */
    @Query("SELECT lc.collateralThreshold FROM LoanCriteria lc WHERE lc.criteriaId = 1")
    double findMinCollateralThreshold();
}
