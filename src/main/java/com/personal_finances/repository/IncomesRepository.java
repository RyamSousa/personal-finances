package com.personal_finances.repository;

import com.personal_finances.model.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomesRepository extends JpaRepository<Incomes, Long> {

    @Query("SELECT re FROM Incomes re " +
            "WHERE re.category.id = :categoryId")
    List<Optional<Incomes>> findByCategory(Long categoryId);

    Optional<Incomes> findById(Long id);
}
