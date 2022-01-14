package com.personal_finances.controller;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados dos usuários")
@RestController
@RequestMapping(value = "/api/user")
public interface UsersController {

    @ApiOperation("Busca um usuário por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findById(@PathVariable Long id);

    @ApiOperation("Busca um usuário por cpf")
    @GetMapping(value = "/cpf/{cpf}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findByCpf(@PathVariable String cpf);

    @ApiOperation("Busca todos os usuário")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> findAllUsers();

    @ApiOperation("Busca todas as contas de um determinado usuário")
    @GetMapping(value = "/accounts/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Accounts>> findAllAccountsByUser(@PathVariable Long id);

    @ApiOperation("Cria um novo usuário")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> save(@Valid @RequestBody Users user);

    @ApiOperation("Atualiza os dados do usuário")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> update(@Valid @RequestBody Users user);

    @ApiOperation("Deleta um usuário por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> delete(@PathVariable Long id);
}
