package com.personal_finances.controller.impl;

import com.personal_finances.controller.RoleController;
import com.personal_finances.model.dto.RoleDTO;
import com.personal_finances.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role){
        return ResponseEntity.ok(roleService.save(role));
    }

    @Override
    public ResponseEntity<List<RoleDTO>> findAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
