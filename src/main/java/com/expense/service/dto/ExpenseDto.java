package com.expense.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {
    @JsonProperty(value = "external_id")
    private String externalId;

    // TODO: Make amount BigDecimal

    @JsonProperty(value = "amount")
    private String amount;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "merchant")
    private String merchant;

    @NonNull
    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    public ExpenseDto(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            ExpenseDto expenseDto = objectMapper.readValue(json, ExpenseDto.class);
            this.externalId = expenseDto.getExternalId();
            this.amount = expenseDto.getAmount();
            this.userId = expenseDto.getUserId();
            this.merchant = expenseDto.getMerchant();
            this.currency = expenseDto.getCurrency();
            this.type = expenseDto.getType();
            this.createdAt = expenseDto.getCreatedAt();
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize ExpenseDto from JSON", e);
        }
    }
}
