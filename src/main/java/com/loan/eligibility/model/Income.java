package com.loan.eligibility.model;

import javax.persistence.*;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer incomeId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    @Column(name = "income_source", nullable = false)
    private String incomeSource;

    @Column(name = "verification_status")
    private String verificationStatus;

    @Column(name = "verification_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date verificationDate;

    // Getters and Setters

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public java.util.Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(java.util.Date verificationDate) {
        this.verificationDate = verificationDate;
    }
}
