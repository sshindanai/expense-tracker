package com.sshindanai.springbootmongodbtutorial.controller;

import com.sshindanai.springbootmongodbtutorial.model.Expense;
import com.sshindanai.springbootmongodbtutorial.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Expense expense) {
        expenseService.add(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Expense expense) {
        expenseService.update(expense);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        return ResponseEntity.ok(expenseService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Expense> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(expenseService.getByName(name));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        expenseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
