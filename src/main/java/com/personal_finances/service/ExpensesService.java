package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Expenses;
import com.personal_finances.repository.ExpensesRepository;
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
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    private final CategoriesService categoriesService;

    private final AccountsService accountsService;

    public Expenses save(Expenses expense){

        Accounts account = accountsService.findByAccountNumber(expense.getAccount().getAccountNumber());

        categoriesService.findById(expense.getCategory().getId());

        expensesRepository.save(expense);

        return expense;
    }

    public Expenses update(Expenses expense){

        Accounts account = accountsService.findByAccountNumber(expense.getAccount().getAccountNumber());

        categoriesService.findById(expense.getCategory().getId());

        expensesRepository.save(expense);

        return expense;
    }

    public Expenses delete(Long id){
        Expenses expense = this.findById(id);

        expensesRepository.deleteById(id);

        return expense;
    }

    public Expenses findById(Long id){
        Optional<Expenses> optionalExpenses = expensesRepository.findById(id);

        if (optionalExpenses.isEmpty()) {
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalExpenses.get();
    }

    public List<Expenses> findByCategory(Long id){
        List<Optional<Expenses>> optionalExpenses = expensesRepository.findByCategory(id);

        if (optionalExpenses.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalExpenses.stream()
                .map(expenses -> expenses.get())
                .collect(Collectors.toList());
    }

    public List<Expenses> findAllExpenses(){
        List<Expenses> lst =expensesRepository.findAll();

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<Expenses> findExpensesByDate(Long id, String date){

        date = GetDate.extractMonthAndYear(date);

        List<Optional<Expenses>> lst = expensesRepository.findExpensesByDate(id, date);

        return lst.stream().map(expenses -> expenses.get()).collect(Collectors.toList());
    }

}
