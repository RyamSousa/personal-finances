package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Users;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.UsersRepository;
import com.personal_finances.utils.MessagesExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapperUsers;

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    public UsersDTO save(UsersDTO dto){
        UsersDTO userValidation = this.findByCpf(dto.getCpf());

        if(!(userValidation == null)){
            throw new BusinessException(MessagesExceptions.DATA_ALREADY_EXISTS);
        }

        Users user = mapperUsers.toUsers(dto);
        repository.save(user);

        return mapperUsers.toDto(user);
    }

    public UsersDTO update(UsersDTO dto){
        UsersDTO userValidation = this.findByCpf(dto.getCpf());

        if(userValidation == null){
            throw new BusinessException(MessagesExceptions.USER_NOT_FOUND);
        }

        dto.setId(userValidation.getId());

        Users user = mapperUsers.toUsers(dto);
        repository.save(user);

        return mapperUsers.toDto(user);
    }

    public UsersDTO delete(Long id){
        UsersDTO userValidation = this.findById(id);

        List<AccountsDTO> accounts = this.findAllAccountsByUser(userValidation.getId());

        for (AccountsDTO ac: accounts) {
            accountsRepository.deleteById(ac.getId());
        }

        repository.deleteById(userValidation.getId());

        return userValidation;
    }

    public UsersDTO findById(Long id){
        Optional<Users> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BusinessException(MessagesExceptions.USER_NOT_FOUND);
        }

        return mapperUsers.optionalToDto(optionalUser);
    }

    public UsersDTO findByCpf(String cpf){
        Optional<Users> optionalUser = repository.findByCpf(cpf);
        UsersDTO dto = null;

        if (optionalUser.isPresent()){
            dto = mapperUsers.optionalToDto(optionalUser);
        }

        return dto;
    }

    public List<UsersDTO> findAllUsers(){
        List<UsersDTO> lst = mapperUsers.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<AccountsDTO> findAllAccountsByUser(Long id) {

        List<AccountsDTO> lst = repository.findAllAccountsByUser(id)
                .stream().map(accountMapper::optionaltoDto).collect(Collectors.toList());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

}
