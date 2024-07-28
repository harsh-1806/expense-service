package com.expense.service.controllers;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public ResponseEntity<List<ExpenseDto>> getExpenses(
            @PathParam("user_id")
            @NonNull
            String userId
    ) {
        try {
            List<ExpenseDto> expenseDtoList  = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
