package com.example.controller;

import com.example.dto.LoanEligibilityRequest;
import com.example.dto.LoanEligibilityResponse;
import com.example.service.LoanEligibilityService;
import com.example.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LendingController {



    @Autowired
    private LoanEligibilityService eligibilityService;

    @PostMapping("/check-eligibility")
    public ResponseEntity<?> checkLoanEligibility(@RequestBody LoanEligibilityRequest request, @RequestHeader("token") String token) {
        if(!TokenUtil.hasAccess(token, request.getUserId()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not authorised to see  other user's eligibility");
        LoanEligibilityResponse response = eligibilityService.checkEligibility(request);
        return ResponseEntity.ok(response);
    }

}
