package com.personal_finances.controller;

import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados dos usuários")
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UsersService usersService;

    @ApiOperation("Cria um novo usuário")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> save(@Valid @RequestBody UsersDTO dto){
        return ResponseEntity.ok(usersService.save(dto));
    }

    @ApiOperation("Busca um usuário por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findById(id));
    }

    @ApiOperation("Busca um usuário por cpf")
    @GetMapping(value = "/cpf/{cpf}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(usersService.findByCpf(cpf));
    }

    @ApiOperation("Deleta um usuário por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(usersService.delete(id));
    }

    @ApiOperation("Busca todos os usuário")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDTO>> findAllUsers(){
        return ResponseEntity.ok(usersService.findAllUsers());
    }
}
