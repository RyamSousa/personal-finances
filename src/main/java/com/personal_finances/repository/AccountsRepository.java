package com.personal_finances.repository;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Revenues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    @Query("SELECT ac FROM Accounts ac " +
            "INNER JOIN Users us ON (us.cpf = ac.user.cpf) " +
            "WHERE ac.accountNumber = :accountNumber " +
            "AND us.cpf = :cpf")
    Optional<Accounts> findByAccountNumberUser(Long accountNumber, String cpf);

    @Query("SELECT re FROM Accounts ac " +
            "INNER JOIN Revenues re ON (ac.id = re.account.id) " +
            "WHERE re.account.id = :id ")
    List<Optional<Revenues>> findAllRevenuesForAccount(Long id);

    Optional<Accounts> findByAccountNumber(Long accountNumber);
}
