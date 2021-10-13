package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.LoginUserMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Logins;
import com.personal_finances.model.Users;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.LoginUserDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.UsersRepository;
import com.personal_finances.utils.RolesUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.personal_finances.utils.MessagesExceptions.*;
import static com.personal_finances.utils.RolesUsers.ROLE_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapperUsers;

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    private final LoginUserService loginUserService;
    private final LoginUserMapper loginUserMapper;

    public UsersDTO save(UsersDTO dto){
        Optional<Users> user = repository.findByCpf(dto.getCpf());

        if(user.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }

        loginUserService.findByUsername(dto.getLogin());

        dto.getLogin().setPassword(loginUserService.passwordEncode(dto.getLogin().getPassword()));
        Users save = repository.save(mapperUsers.toUsers(dto));
        loginUserService.addRoleToLogin(dto.getLogin().getUsername(), ROLE_USER);

        return mapperUsers.toDto(save);
    }

    public UsersDTO update(UsersDTO dto){
        Optional<Users> user = repository.findByCpf(dto.getCpf());

        if(user.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        LoginUserDTO loginDTO = loginUserService.findByUsername(dto.getLogin().getUsername());
        Logins login = loginUserMapper.toLoginUser(loginDTO);
        login.setPassword(loginUserService.passwordEncode(dto.getLogin().getPassword()));

        dto.setLogin(login);
        Users save = repository.save(mapperUsers.toUsers(dto));
        loginUserService.update(loginUserMapper.toDto(login));

        return mapperUsers.toDto(save);
    }

    public UsersDTO delete(Long id){
        UsersDTO userValidation = this.findById(id);

        List<AccountsDTO> accounts = this.findAllAccountsByUser(userValidation.getId());

        if(!accounts.isEmpty()){
            for (AccountsDTO ac: accounts) {
                accountsRepository.deleteById(ac.getId());
            }
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
        return repository.findAllAccountsByUser(id)
                .stream().map(accountMapper::optionalToDto).collect(Collectors.toList());
    }

}
