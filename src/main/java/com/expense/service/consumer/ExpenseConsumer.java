package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.expense.service.service.ExpenseService;

@Service
public class ExpenseConsumer {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseConsumer(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // TODO : Add distributed lock using redis

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto eventData) {
        try {
            expenseService.createExpense(eventData);
        }
        catch (Exception e) {
            System.out.println("Exception in Expense Consumer");
        }
    }
}
