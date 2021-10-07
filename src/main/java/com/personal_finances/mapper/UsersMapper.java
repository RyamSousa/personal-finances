package com.personal_finances.mapper;

import com.personal_finances.model.Users;
import com.personal_finances.model.dto.UsersDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsersMapper {
    public UsersDTO toDto(Users user) {
        UsersDTO dto = new UsersDTO();

        dto.setId(user.getId());
        dto.setCpf(user.getCpf());
        dto.setName(user.getName());
        dto.setBirthdate(user.getBirthdate());

        return dto;
    }

    public UsersDTO optionalToDto(Optional<Users> user) {
        UsersDTO dto = new UsersDTO();

        dto.setId(user.get().getId());
        dto.setCpf(user.get().getCpf());
        dto.setName(user.get().getName());
        dto.setBirthdate(user.get().getBirthdate());

        return dto;
    }

    public Users toUsers(UsersDTO dto) {
        Users user = new Users();

        user.setId(dto.getId());
        user.setCpf(dto.getCpf());
        user.setName(dto.getName());
        user.setBirthdate(dto.getBirthdate());

        return user;
    }

    public List<UsersDTO> toListDTO(List<Users> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
