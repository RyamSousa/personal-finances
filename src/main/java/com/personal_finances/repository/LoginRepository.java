package com.personal_finances.repository;

import com.personal_finances.model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Logins, Long> {

    Optional<Logins> findByUsername(String username);

}
