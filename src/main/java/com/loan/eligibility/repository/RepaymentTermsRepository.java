package com.loan.eligibility.repository;

import com.loan.eligibility.model.RepaymentTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentTermsRepository extends JpaRepository<RepaymentTerms, Integer> {
    List<RepaymentTerms> findByLoanAmount(double loanAmount);
}
