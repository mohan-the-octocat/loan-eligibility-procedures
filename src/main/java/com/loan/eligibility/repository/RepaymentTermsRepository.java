package com.loan.eligibility.repository;

import com.loan.eligibility.model.RepaymentTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing repayment terms data.
 * This interface extends JpaRepository to provide CRUD operations for the RepaymentTerms entity.
 */
@Repository
public interface RepaymentTermsRepository extends JpaRepository<RepaymentTerms, Integer> {

    /**
     * Finds the repayment terms based on the loan amount.
     *
     * @param loanAmount the loan amount
     * @return the repayment terms
     */
    @Query("SELECT rt FROM RepaymentTerms rt WHERE rt.minAmount <= :loanAmount AND rt.maxAmount >= :loanAmount")
    RepaymentTerms findRepaymentTerms(double loanAmount);
}
