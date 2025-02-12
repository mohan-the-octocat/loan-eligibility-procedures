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

import java.util.List;

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
        Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);
        if (customer == null) {
            return new EligibilityResponse(false, "Customer not found");
        }

        List<Income> incomes = incomeRepository.findByCustomerId(request.getCustomerId());
        List<Collateral> collaterals = collateralRepository.findByCustomerId(request.getCustomerId());
        LoanCriteria loanCriteria = loanCriteriaRepository.findById(1).orElse(null);

        if (loanCriteria == null) {
            return new EligibilityResponse(false, "Loan criteria not found");
        }

        boolean incomeEligible = checkIncomeEligibility(incomes, loanCriteria);
        boolean collateralEligible = checkCollateralEligibility(collaterals, loanCriteria);
        double riskScore = calculateRiskScore(customer.getCreditScore(), incomes, collaterals);

        boolean isEligible = incomeEligible && collateralEligible && riskScore > 0.5;
        double minAmount = isEligible ? incomes.stream().mapToDouble(Income::getMonthlyIncome).sum() * 0.2 : 0;
        double maxAmount = isEligible ? incomes.stream().mapToDouble(Income::getMonthlyIncome).sum() * 0.6 : 0;

        return new EligibilityResponse(isEligible, minAmount, maxAmount, riskScore);
    }

    private boolean checkIncomeEligibility(List<Income> incomes, LoanCriteria loanCriteria) {
        double totalIncome = incomes.stream().mapToDouble(Income::getMonthlyIncome).sum();
        return totalIncome >= loanCriteria.getIncomeThreshold();
    }

    private boolean checkCollateralEligibility(List<Collateral> collaterals, LoanCriteria loanCriteria) {
        double totalCollateralValue = collaterals.stream().mapToDouble(Collateral::getVehicleValue).sum();
        return totalCollateralValue >= loanCriteria.getCollateralThreshold();
    }

    private double calculateRiskScore(int creditScore, List<Income> incomes, List<Collateral> collaterals) {
        double totalIncome = incomes.stream().mapToDouble(Income::getMonthlyIncome).sum();
        double totalCollateralValue = collaterals.stream().mapToDouble(Collateral::getVehicleValue).sum();
        return (creditScore / 800.0) * (totalIncome / totalCollateralValue);
    }

    public RepaymentTermsResponse getRepaymentTerms(double loanAmount) {
        List<RepaymentTerms> terms = repaymentTermsRepository.findByLoanAmount(loanAmount);
        if (terms.isEmpty()) {
            return new RepaymentTermsResponse("No repayment terms found for the given loan amount");
        }
        return new RepaymentTermsResponse(terms);
    }
}
