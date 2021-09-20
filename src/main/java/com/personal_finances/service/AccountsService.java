package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.ExpensesMapper;
import com.personal_finances.mapper.IncomesMapper;
import com.personal_finances.mapper.UsersMapper;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.model.dto.IncomesDTO;
import com.personal_finances.model.dto.UsersDTO;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.repository.ExpensesRepository;
import com.personal_finances.repository.IncomesRepository;
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

    private final IncomesRepository incomesRepository;
    private final IncomesMapper incomesMapper;

    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;

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
                throw new BusinessException(MessagesExceptions.ACCOUNT_NOT_FOUND);
            }
            account = accountsMapper.toAccounts(dto);
            repository.save(account);
        }else {
            throw new BusinessException(MessagesExceptions.USER_NOT_FOUND);
        }

        return accountsMapper.toDto(account);
    }

    @Transactional
    public AccountsDTO update(AccountsDTO dto){
        UsersDTO user = usersService.findByCpf(dto.getUser().getCpf());
        Accounts account = null;

        if(!(user == null)){
            dto.setUser(usersMapper.toUsers(user));
            Optional<Accounts> optionalAccount = repository.findByAccountNumberUser(
                    dto.getAccountNumber(), dto.getUser().getCpf());

            if (optionalAccount.isEmpty()){
                throw new BusinessException(MessagesExceptions.ACCOUNT_NOT_FOUND);
            }
            dto.setId(optionalAccount.get().getId());
            dto.setCreateDate(optionalAccount.get().getCreateDate());

            account = accountsMapper.toAccounts(dto);
            repository.save(account);
        }else {
            throw new BusinessException(MessagesExceptions.USER_NOT_FOUND);
        }

        return accountsMapper.toDto(account);
    }

    @Transactional
    public AccountsDTO delete(Long id){

        AccountsDTO accountValidation = this.findById(id);

        List<IncomesDTO> incomes = this.findAllIncomesByAccount(accountValidation.getId());
        List<ExpensesDTO> expenses = this.findAllExpensesByAccount(accountValidation.getId());

        if (!incomes.isEmpty()){
            for (IncomesDTO re: incomes) {
                incomesRepository.deleteById(re.getId());
            }
        }
        if (!expenses.isEmpty()){
            for (ExpensesDTO ex: expenses) {
                expensesRepository.deleteById(ex.getId());
            }
        }

        repository.deleteById(accountValidation.getId());

        return accountValidation;
    }

    @Transactional(readOnly = true)
    public AccountsDTO findById(Long id){
        Optional<Accounts> optionalAccount = repository.findById(id);

        if (optionalAccount.isEmpty()) {
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }
        return accountsMapper.optionaltoDto(optionalAccount);
    }

    @Transactional
    public AccountsDTO findByAccountNumber(Long accountNumber){
        Optional<Accounts> account = repository.findByAccountNumber(accountNumber);
        if(account.isEmpty()){
            throw new BusinessException(MessagesExceptions.ACCOUNT_NOT_FOUND);
        }

        return accountsMapper.optionaltoDto(account);
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
    public List<IncomesDTO> findAllIncomesByAccount(Long id){

        List<IncomesDTO> lst = repository.findAllIncomesByAccount(id)
                .stream().map(incomesMapper::optionalToDto).collect(Collectors.toList());

        return lst;
    }

    @Transactional
    public List<ExpensesDTO> findAllExpensesByAccount(Long id){

        List<ExpensesDTO> lst = repository.findAllExpensesByAccount(id)
                .stream().map(expensesMapper::optionalToDto).collect(Collectors.toList());

        return lst;
    }
}
