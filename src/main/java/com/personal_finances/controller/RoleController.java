package com.personal_finances.controller;

import com.personal_finances.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/role")
public interface RoleController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody Role role);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> findAllRoles();
}
