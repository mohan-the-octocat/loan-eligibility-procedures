package com.loan.eligibility.controller;

import com.loan.eligibility.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @PostMapping("/calculate-eligibility")
    public EligibilityResponse calculateEligibility(@RequestBody EligibilityRequest request) {
        return eligibilityService.calculateEligibility(request);
    }

    @GetMapping("/repayment-terms/{loanAmount}")
    public RepaymentTermsResponse getRepaymentTerms(@PathVariable("loanAmount") double loanAmount) {
        return eligibilityService.getRepaymentTerms(loanAmount);
    }
}
