package com.personal_finances.service;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsService {

    private final AccountsRepository repository;
    private final UsersRepository usersRepository;
    private final AccountMapper accountsMapper;

    @Transactional
    public AccountsDTO save(AccountsDTO dto){
//        Optional<Accounts> optionalAccount = repository.findByAccountNumber(
//                dto.getAccountNumber(), dto.getUser().getCpf());
//
//        dto.getUser() = usersRepository.findById(dto.getId());
//        if (optionalAccount.isPresent()){
//            System.out.println("Lançar exceção");
//        }

        Accounts account = accountsMapper.toAccounts(dto);
        repository.save(account);

        return accountsMapper.toDto(account);
    }

    @Transactional
    public AccountsDTO delete(Long id){
        AccountsDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public AccountsDTO findById(Long id){
        Optional<Accounts> optionalAccount = repository.findById(id);

        if (optionalAccount.isEmpty()) {
            System.out.println("Lançar exceção");
        }
        return accountsMapper.optionaltoDto(optionalAccount);
    }

    @Transactional(readOnly = true)
    public List<AccountsDTO> findAllCategories(){
        return accountsMapper.toListDTO(repository.findAll());
    }
}
