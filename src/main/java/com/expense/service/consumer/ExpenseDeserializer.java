package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        ExpenseDto expenseDto = null;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            expenseDto = objectMapper.readValue(bytes, ExpenseDto.class);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return expenseDto;
    }
}
