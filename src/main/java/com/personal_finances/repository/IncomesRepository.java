package com.personal_finances.repository;

import com.personal_finances.model.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomesRepository extends JpaRepository<Incomes, Long> {

    @Query("SELECT i FROM Incomes i " +
            "WHERE i.category.id = :categoryId")
    List<Optional<Incomes>> findByCategory(Long categoryId);

    @Query("SELECT i FROM Accounts ac " +
            "INNER JOIN Incomes i ON (ac.id = i.account.id) " +
            "WHERE ac.id = :id AND i.date LIKE %:date")
    List<Optional<Incomes>> findIncomesByDate(Long id, String date);
}
