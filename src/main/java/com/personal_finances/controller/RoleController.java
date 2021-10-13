package com.personal_finances.controller;

import com.personal_finances.model.dto.RoleDTO;
import com.personal_finances.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role){
        return ResponseEntity.ok(roleService.save(role));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> findAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
