package com.personal_finances.controller.impl;

import com.personal_finances.controller.UsersController;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {

    private final UsersService usersService;

    @Override
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findById(id));
    }

    @Override
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(usersService.findByCpf(cpf));
    }

    @Override
    public ResponseEntity<List<UsersDTO>> findAllUsers(){
        return ResponseEntity.ok(usersService.findAllUsers());
    }

    @Override
    public ResponseEntity<List<AccountsDTO>> findAllAccountsByUser(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findAllAccountsByUser(id));
    }

    @Override
    public ResponseEntity<UsersDTO> save(@Valid @RequestBody UsersDTO dto){
        return ResponseEntity.ok(usersService.save(dto));
    }

    @Override
    public ResponseEntity<UsersDTO> update(@Valid @RequestBody UsersDTO dto){
        return ResponseEntity.ok(usersService.update(dto));
    }

    @Override
    public ResponseEntity<UsersDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(usersService.delete(id));
    }
}
