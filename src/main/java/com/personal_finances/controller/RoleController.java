package com.personal_finances.controller;

import com.personal_finances.model.dto.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/role")
public interface RoleController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> findAllRoles();
}
