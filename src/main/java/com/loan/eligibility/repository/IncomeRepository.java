package com.loan.eligibility.repository;

import com.loan.eligibility.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findByCustomerId(Integer customerId);
}
