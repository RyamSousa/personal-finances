package com.personal_finances.service;

import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Users;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapperUsers;
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public UsersDTO save(UsersDTO dto){
        UsersDTO userValidation = this.findByCpf(dto.getCpf());

        if(!(userValidation == null)){
            throw new RuntimeException("Usuário já existe");
        }
        Users user = mapperUsers.toUsers(dto);
        repository.save(user);

        return mapperUsers.toDto(user);
    }

    @Transactional
    public UsersDTO delete(Long id){
        UsersDTO userValidation = this.findById(id);

        List<AccountsDTO> accounts = this.findAllAccountsForUser(userValidation.getId());

        for (AccountsDTO ac: accounts) {
            accountsRepository.deleteById(ac.getId());
        }

        repository.deleteById(userValidation.getId());

        return userValidation;
    }

    @Transactional(readOnly = true)
    public UsersDTO findById(Long id){
        Optional<Users> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            System.out.println("Usuário não existe");
        }

        return mapperUsers.optionaltoDto(optionalUser);
    }

    @Transactional(readOnly = true)
    public UsersDTO findByCpf(String cpf){
        Optional<Users> optionalUser = repository.findByCpf(cpf);
        UsersDTO dto = null;

        if (!(optionalUser.isEmpty())){
            dto = mapperUsers.optionaltoDto(optionalUser);
        }

        return dto;
    }

    @Transactional(readOnly = true)
    public List<UsersDTO> findAllUsers(){
        return mapperUsers.toListDTO(repository.findAll());
    }

    public List<AccountsDTO> findAllAccountsForUser(Long id) {
        return accountMapper.toListDTO(repository.findAllAccountsForUser(id));
    }

}
