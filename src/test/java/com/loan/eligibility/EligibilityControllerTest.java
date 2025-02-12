package com.loan.eligibility;

import com.loan.eligibility.controller.EligibilityController;
import com.loan.eligibility.service.EligibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EligibilityControllerTest {

    @Mock
    private EligibilityService eligibilityService;

    @InjectMocks
    private EligibilityController eligibilityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eligibilityController).build();
    }

    @Test
    public void testCalculateEligibility() throws Exception {
        EligibilityRequest request = new EligibilityRequest();
        request.setCustomerId(1);
        request.setCustomerIncome(50000.00);
        request.setCollateralValue(25000.00);
        request.setCreditScore(700);

        EligibilityResponse response = new EligibilityResponse(true, 10000.00, 40000.00, 0.75);

        when(eligibilityService.calculateEligibility(request)).thenReturn(response);

        mockMvc.perform(post("/eligibility/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\":1,\"customerIncome\":50000.00,\"collateralValue\":25000.00,\"creditScore\":700}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"isEligible\":true,\"minAmount\":10000.00,\"maxAmount\":40000.00,\"riskScore\":0.75}"));
    }

    @Test
    public void testGetRepaymentTerms() throws Exception {
        RepaymentTermsResponse response = new RepaymentTermsResponse();
        response.setTermMonths(60);
        response.setInterestRate(0.05);
        response.setMonthlyPayment(500.00);

        when(eligibilityService.getRepaymentTerms(20000.00)).thenReturn(response);

        mockMvc.perform(get("/eligibility/repayment-terms")
                .param("loanAmount", "20000.00"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"termMonths\":60,\"interestRate\":0.05,\"monthlyPayment\":500.00}"));
    }
}
