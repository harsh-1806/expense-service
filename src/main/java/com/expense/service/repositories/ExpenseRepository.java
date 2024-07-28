package com.expense.service.repositories;

import com.expense.service.entities.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, String> {
    List<Expense> findExpenseByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime, Timestamp endTime);
    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);

}
