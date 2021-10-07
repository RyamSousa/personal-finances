package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.RolesMapper;
import com.personal_finances.model.Role;
import com.personal_finances.model.dto.RoleDTO;
import com.personal_finances.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.personal_finances.utils.MessagesExceptions.DATA_ALREADY_EXISTS;
import static com.personal_finances.utils.MessagesExceptions.NO_RECORDS_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolesMapper rolesMapper;

    public RoleDTO save(RoleDTO dto){
        Optional<Role> role = roleRepository.findByName(dto.getName());

        if(role.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }
        Role save = roleRepository.save(rolesMapper.toRole(dto));

        return rolesMapper.toDto(save);
    }

    public RoleDTO delete(String name){
        RoleDTO role = this.findByName(name);

        roleRepository.delete(rolesMapper.toRole(role));

        return role;
    }

    public RoleDTO findByName(String name){
        Optional<Role> role = roleRepository.findByName(name);

        if (role.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }
        return rolesMapper.optionalToDto(role);
    }

    public List<RoleDTO> findAllRoles(){
        return roleRepository.findAll()
                .stream().map(rolesMapper::toDto).collect(Collectors.toList());
    }
}
