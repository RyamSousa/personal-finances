package com.personal_finances.controller.impl;

import com.personal_finances.controller.AccountsController;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.model.dto.IncomesDTO;
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
    public ResponseEntity<AccountsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findById(id));
    }

    @Override
    public ResponseEntity<AccountsDTO> findByAccountNumber(@PathVariable Long accountNumber){
        return ResponseEntity.ok(serviceAccounts.findByAccountNumber(accountNumber));
    }

    @Override
    public ResponseEntity<List<AccountsDTO>> findAllAccounts(){
        return ResponseEntity.ok(serviceAccounts.findAllAccounts());
    }

    @Override
    public ResponseEntity<List<IncomesDTO>> findAllIncomesByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllIncomesByAccount(id));
    }

    @Override
    public ResponseEntity<List<ExpensesDTO>> findAllExpensesByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllExpensesByAccount(id));
    }

    @Override
    public ResponseEntity<AccountsDTO> save(@Valid @RequestBody AccountsDTO dto){
        return ResponseEntity.ok(serviceAccounts.save(dto));
    }

    @Override
    public ResponseEntity<AccountsDTO> update(@Valid @RequestBody AccountsDTO dto){
        return ResponseEntity.ok(serviceAccounts.update(dto));
    }

    @Override
    public ResponseEntity<AccountsDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.delete(id));
    }

}
