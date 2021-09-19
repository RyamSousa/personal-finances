package com.personal_finances.repository;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT ac FROM Accounts ac " +
            "INNER JOIN Users us ON (us.id = ac.user.id) " +
            "WHERE us.id = :id ")
    List<Optional<Accounts>> findAllAccountsByUser(Long id);

    Optional<Users> findByCpf(String cpf);
}
