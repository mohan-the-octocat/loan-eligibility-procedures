package com.loan.eligibility.repository;

import com.loan.eligibility.model.LoanCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanCriteriaRepository extends JpaRepository<LoanCriteria, Integer> {
}
