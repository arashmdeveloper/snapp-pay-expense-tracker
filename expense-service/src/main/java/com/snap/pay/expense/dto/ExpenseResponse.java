package com.snap.pay.expense.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseResponse {
 private Long id;
 private String title;
 private String category;
 private BigDecimal amount;
 private String createdBy;
 private Long createdAt;
}