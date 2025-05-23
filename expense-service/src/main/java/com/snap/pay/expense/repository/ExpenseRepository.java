package com.snap.pay.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap.pay.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	Optional<List<Expense>> findByCreatedBy(String user);
	Optional<List<Expense>> findByCreatedByAndCreatedDateGreaterThan(
	        String createdBy, 
	        long createdDate
	    );

}
