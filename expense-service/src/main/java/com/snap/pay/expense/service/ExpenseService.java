package com.snap.pay.expense.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.snap.pay.expense.dto.ExpenseRequest;
import com.snap.pay.expense.dto.ExpenseResponse;
import com.snap.pay.expense.model.Expense;
import com.snap.pay.expense.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseResponse createExpense(ExpenseRequest request) {
        
        Expense expense = Expense.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .amount(request.getAmount())
                .build();

        Expense saved = repository.save(expense);
        return mapToResponse(saved);
    }
    

    public List<ExpenseResponse> getAll() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<List<Expense>> optExpenses = repository.findByCreatedBy(username);
        return optExpenses
        	    .map(expenses -> expenses.stream()
        	            .map(this::mapToResponse)
        	            .toList())
        	        .orElseThrow(() -> new NotFoundException());
    }
    

    public List<ExpenseResponse> getLastMonth() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
     // Last millisecond of last month
        long lastDay = LocalDate.now()
        	    .withDayOfMonth(1)
        	    .minusDays(1)
        	    .atTime(23, 59, 59, 999_999_999)
        	    .atZone(ZoneId.systemDefault())
        	    .toInstant()
        	    .toEpochMilli();
        Optional<List<Expense>> optExpenses = repository.findByCreatedByAndCreatedDateGreaterThan(username, lastDay);
        return optExpenses
        	    .map(expenses -> expenses.stream()
        	            .map(this::mapToResponse)
        	            .toList())
        	        .orElseThrow(() -> new NotFoundException());
    }

    private ExpenseResponse mapToResponse(Expense expense) {
        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .createdBy(expense.getCreatedBy())
                .createdAt(expense.getCreatedDate())
                .build();
    }
}