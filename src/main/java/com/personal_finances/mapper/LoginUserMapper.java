package com.personal_finances.mapper;

import com.personal_finances.model.LoginUser;
import com.personal_finances.model.dto.LoginUserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserMapper {

    public LoginUserDTO toDto(LoginUser loginUser){
        LoginUserDTO dto = new LoginUserDTO();

        dto.setId(loginUser.getId());
        dto.setUsername(loginUser.getUsername());
        dto.setPassword(loginUser.getPassword());
        dto.setRoles(loginUser.getRoles());

        return dto;
    }

    public LoginUser toLoginUser(LoginUserDTO dto){
        LoginUser loginUser = new LoginUser();

        loginUser.setId(dto.getId());
        loginUser.setUsername(dto.getUsername());
        loginUser.setPassword(dto.getPassword());
        loginUser.setRoles(dto.getRoles());

        return loginUser;
    }

    public LoginUserDTO optionalToDto(Optional<LoginUser> loginUser) {
        LoginUserDTO dto = new LoginUserDTO();

        dto.setId(loginUser.get().getId());
        dto.setUsername(loginUser.get().getUsername());
        dto.setPassword(loginUser.get().getPassword());
        dto.setRoles(loginUser.get().getRoles());

        return dto;
    }
}
