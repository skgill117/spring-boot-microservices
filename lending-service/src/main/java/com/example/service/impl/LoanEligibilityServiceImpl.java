package com.example.service.impl;

import com.example.dto.LoanEligibilityRequest;
import com.example.dto.LoanEligibilityResponse;
import com.example.service.CreditScoreService;
import com.example.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    @Autowired
    private CreditScoreService creditScoreService;

    @Override
    public LoanEligibilityResponse checkEligibility(LoanEligibilityRequest request) {
        int creditScore = creditScoreService.getCreditScore(request.getUserId());

        // Check if credit score meets minimum requirement
        boolean isEligible = creditScore >= 600; // Assume minimum score is 600 for loan eligibility

        // Create response with eligibility details
        LoanEligibilityResponse response = new LoanEligibilityResponse();
        response.setUserId(request.getUserId());
        response.setCreditScore(creditScore);
        response.setEligible(isEligible);

        if (isEligible) {
            // If eligible, suggest potential loan amounts and terms
            response.setEligibleLoanAmount(calculateLoanAmount(creditScore, request.getLoanAmount()));
            response.setLoanTerms("Tenure: " + request.getTenureMonths() + " months, Interest rate: 10%");
        }

        return response;
    }

    private int calculateLoanAmount(int creditScore, int requestedAmount) {
        // Logic to calculate loan amount based on credit score
        if (creditScore > 750) {
            return requestedAmount;
        } else if (creditScore >= 600) {
            return Math.min(requestedAmount, 500000); // Cap loan amount for lower scores
        } else {
            return 0; // Not eligible for any loans
        }
    }
}
