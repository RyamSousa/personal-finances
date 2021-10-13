package com.personal_finances.mapper;

import com.personal_finances.model.Logins;
import com.personal_finances.model.dto.LoginUserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserMapper {

    public LoginUserDTO toDto(Logins loginUser){
        LoginUserDTO dto = new LoginUserDTO();

        dto.setId(loginUser.getId());
        dto.setUsername(loginUser.getUsername());
        dto.setPassword(loginUser.getPassword());
        dto.setRoles(loginUser.getRoles());

        return dto;
    }

    public Logins toLoginUser(LoginUserDTO dto){
        Logins loginUser = new Logins();

        loginUser.setId(dto.getId());
        loginUser.setUsername(dto.getUsername());
        loginUser.setPassword(dto.getPassword());
        loginUser.setRoles(dto.getRoles());

        return loginUser;
    }

    public LoginUserDTO optionalToDto(Optional<Logins> loginUser) {
        LoginUserDTO dto = new LoginUserDTO();

        dto.setId(loginUser.get().getId());
        dto.setUsername(loginUser.get().getUsername());
        dto.setPassword(loginUser.get().getPassword());
        dto.setRoles(loginUser.get().getRoles());

        return dto;
    }
}
