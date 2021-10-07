package com.personal_finances.repository;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Expenses;
import com.personal_finances.model.Incomes;
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

    @Query("SELECT i FROM Accounts ac " +
            "INNER JOIN Incomes i ON (ac.id = i.account.id) " +
            "WHERE i.account.id = :id ")
    List<Optional<Incomes>> findAllIncomesByAccount(Long id);

    @Query("SELECT ex FROM Accounts ac " +
            "INNER JOIN Expenses ex ON (ac.id = ex.account.id) " +
            "WHERE ex.account.id = :id ")
    List<Optional<Expenses>> findAllExpensesByAccount(Long id);

    Optional<Accounts> findByAccountNumber(Long accountNumber);


}
