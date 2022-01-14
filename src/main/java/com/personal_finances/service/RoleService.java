package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Role;
import com.personal_finances.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.personal_finances.utils.MessagesExceptions.DATA_ALREADY_EXISTS;
import static com.personal_finances.utils.MessagesExceptions.NO_RECORDS_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role save(Role role){
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());

        if(optionalRole.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }
        Role save = roleRepository.save(role);

        return save;
    }

    public Role delete(String name){
        Role role = this.findByName(name);

        roleRepository.delete(role);

        return role;
    }

    public Role findByName(String name){
        Optional<Role> role = roleRepository.findByName(name);

        if (role.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }
        return role.get();
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
