package com.example.dto;

import com.example.LoanType;
import lombok.Data;

@Data
public class ExistingLoan {

    private LoanType loanType;
    private Double outstandingAmount;
    private int remainingTenureMonths;
}
