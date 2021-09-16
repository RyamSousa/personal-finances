package com.personal_finances.repository;

import com.personal_finances.model.Expenditures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendituresRepository extends JpaRepository<Expenditures, Long> {
}
