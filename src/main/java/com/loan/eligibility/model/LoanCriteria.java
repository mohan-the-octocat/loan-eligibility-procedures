package com.loan.eligibility.model;

import javax.persistence.*;

@Entity
@Table(name = "loan_criteria")
public class LoanCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer criteriaId;

    @Column(name = "income_threshold")
    private Double incomeThreshold;

    @Column(name = "collateral_threshold")
    private Double collateralThreshold;

    @Column(name = "min_credit_score")
    private Integer minCreditScore;

    @Column(name = "max_ltv_ratio")
    private Double maxLtvRatio;

    @Column(name = "min_income_ratio")
    private Double minIncomeRatio;

    // Getters and Setters

    public Integer getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Double getIncomeThreshold() {
        return incomeThreshold;
    }

    public void setIncomeThreshold(Double incomeThreshold) {
        this.incomeThreshold = incomeThreshold;
    }

    public Double getCollateralThreshold() {
        return collateralThreshold;
    }

    public void setCollateralThreshold(Double collateralThreshold) {
        this.collateralThreshold = collateralThreshold;
    }

    public Integer getMinCreditScore() {
        return minCreditScore;
    }

    public void setMinCreditScore(Integer minCreditScore) {
        this.minCreditScore = minCreditScore;
    }

    public Double getMaxLtvRatio() {
        return maxLtvRatio;
    }

    public void setMaxLtvRatio(Double maxLtvRatio) {
        this.maxLtvRatio = maxLtvRatio;
    }

    public Double getMinIncomeRatio() {
        return minIncomeRatio;
    }

    public void setMinIncomeRatio(Double minIncomeRatio) {
        this.minIncomeRatio = minIncomeRatio;
    }
}
