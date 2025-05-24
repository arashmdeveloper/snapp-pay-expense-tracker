package com.snap.pay.expense.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ExpenseRequest {
	@NotBlank(message = "title is required")
    private String title;
	@NotBlank(message = "category is required")
	private String category;
	@Positive(message = "amount should be positive value")
	private BigDecimal amount;
}

