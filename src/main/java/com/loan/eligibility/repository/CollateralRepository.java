package com.loan.eligibility.repository;

import com.loan.eligibility.model.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollateralRepository extends JpaRepository<Collateral, Integer> {
    List<Collateral> findByCustomerId(Integer customerId);
}
