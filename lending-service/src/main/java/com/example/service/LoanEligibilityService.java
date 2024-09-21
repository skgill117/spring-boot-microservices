package com.example.service;

import com.example.dto.LoanEligibilityRequest;
import com.example.dto.LoanEligibilityResponse;

public interface LoanEligibilityService {
    LoanEligibilityResponse checkEligibility(LoanEligibilityRequest request);
}
