package com.snap.pay.expense.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
//	@NotBlank
    private String title;
//	@NotBlank
	private String category;
//	@Positive
//    @JsonFormat(shape = JsonFormat.Shape.STRING) 
	private BigDecimal amount;
}

