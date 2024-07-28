package com.expense.service.service;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.entities.Expense;
import com.expense.service.repositories.ExpenseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto) {
        setCurrency(expenseDto);
        try {
            expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean updateExpense(ExpenseDto expenseDto) {
        Optional<Expense> expenseFoundOpt = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(), expenseDto.getExternalId());

        if(expenseFoundOpt.isEmpty()) {
            return false;
        }

        Expense expense = expenseFoundOpt.get();

        expense.setCurrency(Strings.isNotBlank(expense.getCurrency()) ? expense.getCurrency() : expenseDto.getCurrency());
        expense.setMerchant(Strings.isNotBlank(expense.getMerchant()) ? expense.getMerchant() : expenseDto.getMerchant());

        expenseRepository.save(expense);
        return true;
    }

    // TODO : Add getExpense() By date

    public List<ExpenseDto> getExpense(String userId) {
        List<Expense> expenseList = expenseRepository.findExpenseByUserId(userId);

        return expenseList.stream().map(e ->
            new ExpenseDto(e.getExternalId(),
                    e.getAmount(),
                    e.getUserId(),
                    e.getMerchant(),
                    e.getCurrency(),
                    e.getType(),
                    e.getCreatedAt())
        ).collect(Collectors.toList());
    }

    private void setCurrency(ExpenseDto expenseDto) {
        if(expenseDto.getCurrency() == null)
            expenseDto.setCurrency("INR");
    }

}
