package com.example.dto;

import lombok.Data;

@Data
public class LoanEligibilityResponse {

    private String userId;
    private int creditScore;
    private boolean isEligible;
    private int eligibleLoanAmount;
    private String loanTerms;
}
