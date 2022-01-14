package com.personal_finances.controller;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Expenses;
import com.personal_finances.model.Incomes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das contas dos usuários")
@RestController
@RequestMapping(value = "/api/accounts")
public interface AccountsController {

    @ApiOperation("Busca uma conta por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> findById(@PathVariable Long id);

    @ApiOperation("Buscar uma conta pelo número")
    @GetMapping(value = "/accountnumber/{accountNumber}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> findByAccountNumber(@PathVariable Long accountNumber);

    @ApiOperation("Busca todas as contas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Accounts>> findAllAccounts();

    @ApiOperation("Busca todas as receitas de uma conta")
    @GetMapping(value = "/incomes/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Incomes>> findAllIncomesByAccount(@PathVariable Long id);

    @ApiOperation("Busca todas as despesas de uma conta")
    @GetMapping(value = "/expenses/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> findAllExpensesByAccount(@PathVariable Long id);

    @ApiOperation("Cria uma nova conta para um usuário. " +
            "SÓ É POSSÍVEL CADASTRAR UMA CONTA SE ELA ESTVER VINCULADA A UM USUÁRIO, BASTA " +
            "ADICIONAR O CAMPO CPF NO OBJETO JSON, E EXCLUIR OS DEMAIS CAMPOS DE 'USER'")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> save(@Valid @RequestBody Accounts account);

    @ApiOperation("Atualiza os dados da conta")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> update(@Valid @RequestBody Accounts account);

    @ApiOperation("Deleta uma conta por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> delete(@PathVariable Long id);
}
