package com.personal_finances.mapper;

import com.personal_finances.model.Role;
import com.personal_finances.model.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RolesMapper {

    public RoleDTO toDto(Role role){
        RoleDTO dto = new RoleDTO();

        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }

    public Role toRole(RoleDTO dto){
        Role role = new Role();

        role.setId(dto.getId());
        role.setName(dto.getName());

        return role;
    }

    public RoleDTO optionalToDto(Optional<Role> role){
        RoleDTO dto = new RoleDTO();

        dto.setId(role.get().getId());
        dto.setName(role.get().getName());

        return dto;
    }
}
