package com.personal_finances.controller;

import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.service.RevenuesService;
import com.personal_finances.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UsersService usersService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> save(@Valid @RequestBody UsersDTO dto){
        return ResponseEntity.ok(usersService.save(dto));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findById(id));
    }

    @GetMapping(value = "/category/{cpf}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(usersService.findByCpf(cpf));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(usersService.delete(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDTO>> findAllUsers(){
        return ResponseEntity.ok(usersService.findAllUsers());
    }
}
