package com.loan.eligibility;

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
import com.loan.eligibility.service.EligibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EligibilityServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private CollateralRepository collateralRepository;

    @Mock
    private LoanCriteriaRepository loanCriteriaRepository;

    @Mock
    private RepaymentTermsRepository repaymentTermsRepository;

    @InjectMocks
    private EligibilityService eligibilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateEligibility() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCreditScore(700);

        Income income1 = new Income();
        income1.setMonthlyIncome(2000.00);
        Income income2 = new Income();
        income2.setMonthlyIncome(3000.00);

        Collateral collateral = new Collateral();
        collateral.setVehicleValue(25000.00);

        LoanCriteria loanCriteria = new LoanCriteria();
        loanCriteria.setIncomeThreshold(4000.00);
        loanCriteria.setCollateralThreshold(20000.00);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(incomeRepository.findByCustomerId(1)).thenReturn(Arrays.asList(income1, income2));
        when(collateralRepository.findByCustomerId(1)).thenReturn(Arrays.asList(collateral));
        when(loanCriteriaRepository.findById(1)).thenReturn(Optional.of(loanCriteria));

        EligibilityRequest request = new EligibilityRequest();
        request.setCustomerId(1);
        request.setCustomerIncome(5000.00);
        request.setCollateralValue(25000.00);
        request.setCreditScore(700);

        EligibilityResponse response = eligibilityService.calculateEligibility(request);

        assertTrue(response.isEligible());
        assertEquals(1000.00, response.getMinAmount());
        assertEquals(3000.00, response.getMaxAmount());
        assertEquals(0.875, response.getRiskScore());
    }

    @Test
    public void testGetRepaymentTerms() {
        RepaymentTerms terms = new RepaymentTerms();
        terms.setTermMonths(60);
        terms.setInterestRate(0.05);
        terms.setMonthlyPayment(500.00);

        when(repaymentTermsRepository.findByLoanAmount(20000.00)).thenReturn(Arrays.asList(terms));

        RepaymentTermsResponse response = eligibilityService.getRepaymentTerms(20000.00);

        assertEquals(1, response.getTerms().size());
        assertEquals(60, response.getTerms().get(0).getTermMonths());
        assertEquals(0.05, response.getTerms().get(0).getInterestRate());
        assertEquals(500.00, response.getTerms().get(0).getMonthlyPayment());
    }
}
