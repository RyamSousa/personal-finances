package com.personal_finances.controller.impl;

import com.personal_finances.controller.UsersController;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Users;
import com.personal_finances.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {

    private final UsersService usersService;

    @Override
    public ResponseEntity<Users> findById(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findById(id));
    }

    @Override
    public ResponseEntity<Users> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(usersService.findByCpf(cpf));
    }

    @Override
    public ResponseEntity<List<Users>> findAllUsers(){
        return ResponseEntity.ok(usersService.findAllUsers());
    }

    @Override
    public ResponseEntity<List<Accounts>> findAllAccountsByUser(@PathVariable Long id){
        return ResponseEntity.ok(usersService.findAllAccountsByUser(id));
    }

    @Override
    public ResponseEntity<Users> save(@Valid @RequestBody Users user){
        return ResponseEntity.ok(usersService.save(user));
    }

    @Override
    public ResponseEntity<Users> update(@Valid @RequestBody Users user){
        return ResponseEntity.ok(usersService.update(user));
    }

    @Override
    public ResponseEntity<Users> delete(@PathVariable Long id){
        return ResponseEntity.ok(usersService.delete(id));
    }
}
