package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Users;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.personal_finances.utils.MessagesExceptions.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapperUsers;

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    public UsersDTO save(UsersDTO dto){
        Optional<Users> user = repository.findByCpf(dto.getCpf());

        if(user.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }

        Users save = repository.save(user.get());

        return mapperUsers.toDto(save);
    }

    public UsersDTO update(UsersDTO dto){
        Optional<Users> user = repository.findByCpf(dto.getCpf());

        if(user.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        dto.setId(user.get().getId());

        Users save = repository.save(mapperUsers.toUsers(dto));

        return mapperUsers.toDto(save);
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
            throw new BusinessException(USER_NOT_FOUND);
        }

        return mapperUsers.optionalToDto(optionalUser);
    }

    public UsersDTO findByCpf(String cpf){
        Optional<Users> optionalUser = repository.findByCpf(cpf);

        if (optionalUser.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return mapperUsers.optionalToDto(optionalUser);
    }

    public List<UsersDTO> findAllUsers(){
        List<UsersDTO> lst = mapperUsers.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<AccountsDTO> findAllAccountsByUser(Long id) {

        List<AccountsDTO> lst = repository.findAllAccountsByUser(id)
                .stream().map(accountMapper::optionaltoDto).collect(Collectors.toList());

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

}
