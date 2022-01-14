package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Expenses;
import com.personal_finances.model.Incomes;
import com.personal_finances.model.Users;
import com.personal_finances.repository.AccountsRepository;
import com.personal_finances.repository.ExpensesRepository;
import com.personal_finances.repository.IncomesRepository;
import com.personal_finances.utils.GetDate;
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
public class AccountsService {

    private final AccountsRepository accountsRepository;

    private final UsersService usersService;

    private final IncomesRepository incomesRepository;

    private final ExpensesRepository expensesRepository;

    public Accounts save(Accounts account){
        Users user = usersService.findByCpf(account.getUser().getCpf());

        if(user != null){
            account.setCreateDate(GetDate.getDateSystem());
            Optional<Accounts> optionalAccount = accountsRepository.findByAccountNumberUser(
                    account.getAccountNumber(), account.getUser().getCpf());

            if (optionalAccount.isPresent()){
                throw new BusinessException(ACCOUNT_NOT_FOUND);
            }
            accountsRepository.save(account);
        }else {
            throw new BusinessException(USER_NOT_FOUND);
        }

        return account;
    }

    public Accounts update(Accounts account){
        Users user = usersService.findByCpf(account.getUser().getCpf());

        if(!(user == null)){
            Optional<Accounts> optionalAccount = accountsRepository.findByAccountNumberUser(
                    account.getAccountNumber(), account.getUser().getCpf());

            if (optionalAccount.isEmpty()){
                throw new BusinessException(ACCOUNT_NOT_FOUND);
            }
            accountsRepository.save(account);
        }else {
            throw new BusinessException(USER_NOT_FOUND);
        }

        return account;
    }

    public Accounts delete(Long id){

        Accounts accountValidation = this.findById(id);

        List<Incomes> incomes = this.findAllIncomesByAccount(accountValidation.getId());
        List<Expenses> expenses = this.findAllExpensesByAccount(accountValidation.getId());

        if (!incomes.isEmpty()){
            for (Incomes re: incomes) {
                incomesRepository.deleteById(re.getId());
            }
        }
        if (!expenses.isEmpty()){
            for (Expenses ex: expenses) {
                expensesRepository.deleteById(ex.getId());
            }
        }

        accountsRepository.deleteById(accountValidation.getId());

        return accountValidation;
    }

    public Accounts findById(Long id){
        Optional<Accounts> optionalAccount = accountsRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            throw new BusinessException(NO_RECORDS_FOUND);
        }
        return optionalAccount.get();
    }

    public Accounts findByAccountNumber(Long accountNumber){
        Optional<Accounts> optionalAccount = accountsRepository.findByAccountNumber(accountNumber);

        if(optionalAccount.isEmpty()){
            throw new BusinessException(ACCOUNT_NOT_FOUND);
        }

        return optionalAccount.get();
    }

    public List<Accounts> findAllAccounts(){
        List<Accounts> lst = accountsRepository.findAll();

        if(lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<Incomes> findAllIncomesByAccount(Long id){

        return accountsRepository.findAllIncomesByAccount(id)
                .stream().map(acc -> acc.get()).collect(Collectors.toList());
    }

    public List<Expenses> findAllExpensesByAccount(Long id){

        return accountsRepository.findAllExpensesByAccount(id)
                .stream().map(acc -> acc.get()).collect(Collectors.toList());
    }
}
