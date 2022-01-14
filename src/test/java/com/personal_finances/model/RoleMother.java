package com.personal_finances.model;

import com.personal_finances.model.dto.RoleDTO;

public class RoleMother {

    public static RoleDTO getRoleDto(){
        return new RoleDTO(null, "USER");
    }

    public static Role getRole(){
        return new Role(null, "USER");
    }
}
