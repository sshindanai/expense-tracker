package com.sshindanai.springbootmongodbtutorial.service;

import com.sshindanai.springbootmongodbtutorial.model.Expense;
import com.sshindanai.springbootmongodbtutorial.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void add(Expense expense) {
        expenseRepository.save(expense);
    }

    public void update(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(() -> new RuntimeException("Expense not found"));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAll() {
       return expenseRepository.findAll();
    }

    public Expense getByName(String name) {
        return expenseRepository.findByName(name).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public void delete(String id) {
        expenseRepository.deleteById(id);
    }
}
