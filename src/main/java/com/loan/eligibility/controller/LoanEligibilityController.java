package com.loan.eligibility.controller;

import com.loan.eligibility.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for loan eligibility endpoints.
 */
@RestController
@RequestMapping("/api/loan-eligibility")
public class LoanEligibilityController {

    @Autowired
    private LoanEligibilityService loanEligibilityService;

    /**
     * Endpoint for calculating loan eligibility.
     *
     * @param request the eligibility request containing customer details
     * @return the eligibility response with the result of the calculation
     */
    @PostMapping("/calculate")
    public EligibilityResponse calculateEligibility(@RequestBody EligibilityRequest request) {
        return loanEligibilityService.calculateEligibility(request);
    }

    /**
     * Endpoint for getting repayment terms based on the loan amount.
     *
     * @param loanAmount the loan amount for which repayment terms are requested
     * @return the repayment terms response with the repayment details
     */
    @GetMapping("/repayment-terms/{loanAmount}")
    public RepaymentTermsResponse getRepaymentTerms(@PathVariable("loanAmount") double loanAmount) {
        return loanEligibilityService.getRepaymentTerms(loanAmount);
    }
}
