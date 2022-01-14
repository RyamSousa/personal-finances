package com.personal_finances.controller.impl;

import com.personal_finances.controller.RoleController;
import com.personal_finances.model.Role;
import com.personal_finances.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.save(role));
    }

    @Override
    public ResponseEntity<List<Role>> findAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
