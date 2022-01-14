package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Logins;
import com.personal_finances.model.Users;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
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

    private final UsersRepository userRepository;

    private final AccountsRepository accountsRepository;

    private final LoginUserService loginUserService;

    public Users save(Users user){
        Optional<Users> optionalUser = userRepository.findByCpf(user.getCpf());

        if(optionalUser.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }

        loginUserService.findByUsername(user.getLogin());

        user.getLogin().setPassword(loginUserService.passwordEncode(user.getLogin().getPassword()));
        Users save = userRepository.save(user);
        loginUserService.addRoleToLogin(user.getLogin().getUsername(), ROLE_USER);

        return save;
    }

    public Users update(Users user){
        Optional<Users> optionalUser = userRepository.findByCpf(user.getCpf());

        if(optionalUser.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        Logins login = loginUserService.findByUsername(user.getLogin().getUsername());
        login.setPassword(loginUserService.passwordEncode(user.getLogin().getPassword()));

        user.setLogin(login);
        Users save = userRepository.save(user);
        loginUserService.update(login);

        return save;
    }

    public Users delete(Long id){
        Users userValidation = this.findById(id);

        List<Accounts> accounts = this.findAllAccountsByUser(userValidation.getId());

        if(!accounts.isEmpty()){
            for (Accounts ac: accounts) {
                accountsRepository.deleteById(ac.getId());
            }
        }

        userRepository.deleteById(userValidation.getId());

        return userValidation;
    }

    public Users findById(Long id){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BusinessException(USER_NOT_FOUND);
        }

        return optionalUser.get();
    }

    public Users findByCpf(String cpf){
        Optional<Users> optionalUser = userRepository.findByCpf(cpf);

        if (optionalUser.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalUser.get();
    }

    public List<Users> findAllUsers(){
        List<Users> lst = userRepository.findAll();

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<Accounts> findAllAccountsByUser(Long id) {
        return userRepository.findAllAccountsByUser(id)
                .stream().map(user -> user.get()).collect(Collectors.toList());
    }

}
