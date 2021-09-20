package com.personal_finances.controller;

import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.model.dto.IncomesDTO;
import com.personal_finances.service.AccountsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das contas dos usuários")
@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsController {

    private final AccountsService serviceAccounts;

    @ApiOperation("Cria uma nova conta para um usuário. " +
            "SÓ É POSSÍVEL CADASTRAR UMA CONTA SE ELA ESTVER VINCULADA A UM USUÁRIO, BASTA " +
            "ADICIONAR O CAMPO CPF NO OBJETO JSON, E EXCLUIR OS DEMAIS CAMPOS DE 'USER'")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> save(@Valid @RequestBody AccountsDTO dto){
        return ResponseEntity.ok(serviceAccounts.save(dto));
    }

    @ApiOperation("Atualiza os dados da conta")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> update(@Valid @RequestBody AccountsDTO dto){
        return ResponseEntity.ok(serviceAccounts.update(dto));
    }

    @ApiOperation("Busca uma conta por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findById(id));
    }

    @ApiOperation("Deleta uma conta por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.delete(id));
    }

    @ApiOperation("Buscar uma conta pelo número")
    @GetMapping(value = "/accountnumber/{accountNumber}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> findByAccountNumber(@PathVariable Long accountNumber){
        return ResponseEntity.ok(serviceAccounts.findByAccountNumber(accountNumber));
    }

    @ApiOperation("Busca todas as contas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountsDTO>> findAllAccounts(){
        return ResponseEntity.ok(serviceAccounts.findAllAccounts());
    }
    @ApiOperation("Busca todas as receitas de uma conta")
    @GetMapping(value = "/incomes/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findAllRevenuesByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllRevenuesByAccount(id));
    }

    @ApiOperation("Busca todas as despesas de uma conta")
    @GetMapping(value = "/expenses/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpensesDTO>> findAllExpendituresByAccount(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findAllExpendituresByAccount(id));
    }



}
