package com.loan.eligibility.repository;

import com.loan.eligibility.model.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralRepository extends JpaRepository<Collateral, Integer> {
}
