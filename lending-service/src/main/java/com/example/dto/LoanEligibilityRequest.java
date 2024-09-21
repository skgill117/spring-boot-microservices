package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoanEligibilityRequest {
    private String userId;
    private String location;
    private int loanAmount;
    private int tenureMonths;
    private int income;
    private String employmentStatus;
    private List<ExistingLoan> existingLoans;
    private int creditScore;
    private int internalScore;
    private String kycStatus;
}
