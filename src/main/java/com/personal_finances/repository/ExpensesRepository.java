package com.personal_finances.repository;

import com.personal_finances.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    @Query("SELECT ex FROM Expenses ex " +
            "WHERE ex.category.id = :categoryId")
    List<Optional<Expenses>> findByCategory(Long categoryId);

    @Query("SELECT ex FROM Accounts ac " +
            "INNER JOIN Expenses ex ON (ac.id = ex.account.id) " +
            "WHERE ac.id = :id AND ex.date LIKE %:date")
    List<Optional<Expenses>> findExpensesByDate(Long id, String date);
}
