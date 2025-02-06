package com.loan.eligibility.service;

import com.loan.eligibility.repository.CustomerRepository;
import com.loan.eligibility.repository.IncomeRepository;
import com.loan.eligibility.repository.CollateralRepository;
import com.loan.eligibility.repository.LoanCriteriaRepository;
import com.loan.eligibility.repository.RepaymentTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for loan eligibility calculations and repayment terms.
 */
@Service
public class LoanEligibilityService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private LoanCriteriaRepository loanCriteriaRepository;

    @Autowired
    private RepaymentTermsRepository repaymentTermsRepository;

    /**
     * Calculates the loan eligibility for a customer based on their income, collateral value, and credit score.
     *
     * @param customerId the ID of the customer
     * @param customerIncome the income of the customer
     * @param collateralValue the value of the collateral
     * @param creditScore the credit score of the customer
     * @return true if the customer is eligible for a loan, false otherwise
     */
    @Transactional
    public boolean calculateEligibility(int customerId, double customerIncome, double collateralValue, int creditScore) {
        boolean incomeEligible = checkIncome(customerIncome);
        boolean collateralEligible = checkCollateral(collateralValue);
        double riskScore = calculateRiskScore(creditScore, customerIncome, collateralValue);

        return incomeEligible && collateralEligible && riskScore > 0.5;
    }

    /**
     * Checks if the customer's income meets the minimum threshold.
     *
     * @param customerIncome the income of the customer
     * @return true if the income meets the minimum threshold, false otherwise
     */
    private boolean checkIncome(double customerIncome) {
        double minIncome = loanCriteriaRepository.findMinIncomeThreshold();
        return customerIncome >= minIncome;
    }

    /**
     * Checks if the collateral value meets the minimum threshold.
     *
     * @param collateralValue the value of the collateral
     * @return true if the collateral value meets the minimum threshold, false otherwise
     */
    private boolean checkCollateral(double collateralValue) {
        double minCollateral = loanCriteriaRepository.findMinCollateralThreshold();
        return collateralValue >= minCollateral;
    }

    /**
     * Calculates the risk score based on the customer's credit score, income, and collateral value.
     *
     * @param creditScore the credit score of the customer
     * @param customerIncome the income of the customer
     * @param collateralValue the value of the collateral
     * @return the calculated risk score
     */
    private double calculateRiskScore(int creditScore, double customerIncome, double collateralValue) {
        return (creditScore / 800.0) * (customerIncome / collateralValue);
    }

    /**
     * Retrieves the repayment terms based on the loan amount.
     *
     * @param loanAmount the loan amount
     * @return the repayment terms
     */
    public double getRepaymentTerms(double loanAmount) {
        return repaymentTermsRepository.findRepaymentTerms(loanAmount);
    }
}
