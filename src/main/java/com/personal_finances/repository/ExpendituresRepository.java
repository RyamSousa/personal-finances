package com.personal_finances.repository;

import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpendituresRepository extends JpaRepository<Expenditures, Long> {

    Optional<Expenditures> findById(Long id);
    @Query("SELECT ex FROM Expenditures ex " +
            "WHERE ex.category.id = :categoryId")
    List<Optional<Expenditures>> findByCategory(Long categoryId);
}
