package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.RevenuesMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.utils.GetDate;
import com.personal_finances.utils.MessagesExceptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsService {

    private final AccountsRepository repository;
    private final AccountMapper accountsMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;
    private final RevenuesMapper revenuesMapper;

    @Transactional
    public AccountsDTO save(AccountsDTO dto){
        UsersDTO user = usersService.findByCpf(dto.getUser().getCpf());
        Accounts account = null;

        if(!(user == null)){
            dto.setUser(usersMapper.toUsers(user));
            dto.setCreateDate(GetDate.getDateSystem());
            Optional<Accounts> optionalAccount = repository.findByAccountNumberUser(
                    dto.getAccountNumber(), dto.getUser().getCpf());

            if (optionalAccount.isPresent()){
                throw new BusinessException(MessagesExceptions.DATA_ALREADY_EXISTS);
            }
            account = accountsMapper.toAccounts(dto);
            repository.save(account);
        }else {
            throw new BusinessException(MessagesExceptions.USER_NOT_FOUND);
        }

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
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }
        return accountsMapper.optionaltoDto(optionalAccount);
    }

    @Transactional(readOnly = true)
    public List<AccountsDTO> findAllAccounts(){
        List<AccountsDTO> lst = accountsMapper.toListDTO(repository.findAll());

        if(lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

    @Transactional
    public List<RevenuesDTO> findAllRevenuesForAccount(Long id){

        List<RevenuesDTO> lst = repository.findAllRevenuesForAccount(id)
                .stream().map(revenuesMapper::optionaltoDto).collect(Collectors.toList());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

    @Transactional
    public AccountsDTO findByAccountNumber(Long accountNumber){
        Optional<Accounts> account = repository.findByAccountNumber(accountNumber);
        if(account.isEmpty()){
            throw new BusinessException(MessagesExceptions.ACCOUNT_NOT_FOUND);
        }

        return accountsMapper.optionaltoDto(account);
    }
}
