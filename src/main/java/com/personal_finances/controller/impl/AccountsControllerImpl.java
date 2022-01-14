package com.personal_finances.controller.impl;

import com.personal_finances.controller.AccountsController;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Expenses;
import com.personal_finances.model.Incomes;
import com.personal_finances.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountsControllerImpl implements AccountsController {

    private final AccountsService serviceAccounts;

    @Override
    public ResponseEntity<Accounts> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findById(id));
    }

    @Override
    public ResponseEntity<Accounts> findByAccountNumber(@PathVariable Long accountNumber){
        return ResponseEntity.ok(serviceAccounts.findByAccountNumber(accountNumber));
    }

    @Override
    public ResponseEntity<List<Accounts>> findAllAccounts(){
        return ResponseEntity.ok(serviceAccounts.findAllAccounts());
    }

    @Override
    public ResponseEntity<List<Incomes>> findAllIncomesByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllIncomesByAccount(id));
    }

    @Override
    public ResponseEntity<List<Expenses>> findAllExpensesByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllExpensesByAccount(id));
    }

    @Override
    public ResponseEntity<Accounts> save(@Valid @RequestBody Accounts account){
        return ResponseEntity.ok(serviceAccounts.save(account));
    }

    @Override
    public ResponseEntity<Accounts> update(@Valid @RequestBody Accounts account){
        return ResponseEntity.ok(serviceAccounts.update(account));
    }

    @Override
    public ResponseEntity<Accounts> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.delete(id));
    }

}
