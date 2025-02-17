package com.loan.eligibility.service;

import com.loan.eligibility.model.Customer;
import com.loan.eligibility.model.Income;
import com.loan.eligibility.model.Collateral;
import com.loan.eligibility.model.LoanCriteria;
import com.loan.eligibility.model.RepaymentTerms;
import com.loan.eligibility.repository.CustomerRepository;
import com.loan.eligibility.repository.IncomeRepository;
import com.loan.eligibility.repository.CollateralRepository;
import com.loan.eligibility.repository.LoanCriteriaRepository;
import com.loan.eligibility.repository.RepaymentTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EligibilityService {

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

    public EligibilityResponse calculateEligibility(EligibilityRequest request) {
        // Check income eligibility
        boolean incomeEligible = checkIncome(request.getCustomerIncome());

        // Check collateral eligibility
        boolean collateralEligible = checkCollateral(request.getCollateralValue());

        // Calculate eligibility based on income and collateral
        boolean isEligible = incomeEligible && collateralEligible;

        // Create response
        EligibilityResponse response = new EligibilityResponse();
        response.setEligible(isEligible);
        if (isEligible) {
            response.setMaxLoanAmount(calculateMaxLoanAmount(request.getCustomerIncome(), request.getCollateralValue()));
        }

        return response;
    }

    private boolean checkIncome(double customerIncome) {
        LoanCriteria criteria = loanCriteriaRepository.findById(1).orElseThrow(() -> new RuntimeException("Loan criteria not found"));
        return customerIncome >= criteria.getIncomeThreshold();
    }

    private boolean checkCollateral(double collateralValue) {
        LoanCriteria criteria = loanCriteriaRepository.findById(1).orElseThrow(() -> new RuntimeException("Loan criteria not found"));
        return collateralValue >= criteria.getCollateralThreshold();
    }

    private double calculateMaxLoanAmount(double customerIncome, double collateralValue) {
        LoanCriteria criteria = loanCriteriaRepository.findById(1).orElseThrow(() -> new RuntimeException("Loan criteria not found"));
        double incomeBasedAmount = customerIncome * criteria.getMaxLtvRatio();
        double collateralBasedAmount = collateralValue * criteria.getMaxLtvRatio();
        return Math.min(incomeBasedAmount, collateralBasedAmount);
    }

    public RepaymentTermsResponse getRepaymentTerms(double loanAmount) {
        RepaymentTerms terms = repaymentTermsRepository.findByLoanAmount(loanAmount)
                .orElseThrow(() -> new RuntimeException("Repayment terms not found"));

        RepaymentTermsResponse response = new RepaymentTermsResponse();
        response.setTermMonths(terms.getTermMonths());
        response.setInterestRate(terms.getInterestRate());
        response.setMonthlyPayment(calculateMonthlyPayment(loanAmount, terms.getInterestRate(), terms.getTermMonths()));

        return response;
    }

    private double calculateMonthlyPayment(double loanAmount, double interestRate, int termMonths) {
        double monthlyRate = interestRate / 12;
        return loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, termMonths)) / (Math.pow(1 + monthlyRate, termMonths) - 1);
    }
}
