package com.sshindanai.springbootmongodbtutorial.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.sshindanai.springbootmongodbtutorial.model.Expense;
import com.sshindanai.springbootmongodbtutorial.model.ExpenseCategory;
import com.sshindanai.springbootmongodbtutorial.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ChangeLog()
public class DatabaseChangeLog {

    @ChangeSet(order="001", id="seedDatabase", author="sshindanai")
    public void seedDatabase(ExpenseRepository expenseRepository) {
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(createNewExpense("Movies Ticket", ExpenseCategory.ENTERTAINMENT, new BigDecimal("200.00")));
        expenseList.add(createNewExpense("Dinner", ExpenseCategory.RESTAURANTS, new BigDecimal("200.00")));
        expenseList.add(createNewExpense("Netflix", ExpenseCategory.ENTERTAINMENT, new BigDecimal("145.00")));
        expenseList.add(createNewExpense("Gym", ExpenseCategory.MISC, new BigDecimal("2000.00")));
        expenseList.add(createNewExpense("Internet", ExpenseCategory.UTILITIES, new BigDecimal("499.00")));

        expenseRepository.insert(expenseList);
    }

    private Expense createNewExpense(String name, ExpenseCategory category, BigDecimal amount) {
        Expense expense = new Expense();
        expense.setExpenseName(name);
        expense.setExpenseCategory(category);
        expense.setExpenseAmount(amount);
        return expense;
    }
}
