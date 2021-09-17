package com.personal_finances.repository;

import com.personal_finances.model.Revenues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenuesRepository extends JpaRepository<Revenues, Long> {

    List<Optional<Revenues>> findByCategory(String category);
}
