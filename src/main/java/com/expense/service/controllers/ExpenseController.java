package com.expense.service.controllers;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/getExpense")
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

    @PostMapping("/addExpense")
    public ResponseEntity<Boolean> addExpense(
            @RequestHeader(name = "X-User-ID")
            String userId,
            @RequestBody
            ExpenseDto expenseDto
    ) {
        try {
            expenseDto.setUserId(userId);
            return new ResponseEntity<>(expenseService.createExpense(expenseDto), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

}
