package com.snap.pay.expense.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snap.pay.expense.dto.ExpenseRequest;
import com.snap.pay.expense.dto.ExpenseResponse;
import com.snap.pay.expense.service.ExpenseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService service;

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(
            @RequestBody @Valid ExpenseRequest request) {
        return ResponseEntity.ok(service.createExpense(request));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAll() throws NotFoundException {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/last-month")
    public ResponseEntity<List<ExpenseResponse>> getLastMonth() throws NotFoundException {
        return ResponseEntity.ok(service.getLastMonth());
    }
}