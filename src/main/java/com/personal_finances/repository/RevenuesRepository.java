package com.personal_finances.repository;

import com.personal_finances.model.Revenues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenuesRepository extends JpaRepository<Revenues, Long> {

    @Query("SELECT re FROM Revenues re " +
            "WHERE re.category.id = :categoryId")
    List<Optional<Revenues>> findByCategory(Long categoryId);

    Optional<Revenues> findById(Long id);
}
