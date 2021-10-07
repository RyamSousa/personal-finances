package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.ExpensesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Expenses;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.repository.ExpensesRepository;
import com.personal_finances.utils.GetDate;
import com.personal_finances.utils.MessagesExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpensesService {

    private final ExpensesRepository repository;
    private final ExpensesMapper mapperExpenses;

    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    public ExpensesDTO save(ExpensesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Expenses expenditure = mapperExpenses.toExpenditure(dto);
        repository.save(expenditure);

        return mapperExpenses.toDto(expenditure);
    }

    public ExpensesDTO update(ExpensesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Expenses expense = mapperExpenses.toExpenditure(dto);
        expense.setId(dto.getId());

        repository.save(expense);

        return mapperExpenses.toDto(expense);
    }

    public ExpensesDTO delete(Long id){
        ExpensesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    public ExpensesDTO findById(Long id){
        Optional<Expenses> optionalExpenses = repository.findById(id);

        if (optionalExpenses.isEmpty()) {
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return mapperExpenses.optionalToDto(optionalExpenses);
    }

    public List<ExpensesDTO> findByCategory(Long id){
        List<Optional<Expenses>> optionalExpenses = repository.findByCategory(id);

        if (optionalExpenses.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return optionalExpenses.stream()
                .map(mapperExpenses::optionalToDto)
                .collect(Collectors.toList());
    }

    public List<ExpensesDTO> findAllExpenses(){
        List<ExpensesDTO> lst = mapperExpenses.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<ExpensesDTO> findExpensesByDate(Long id, String date){

        date = GetDate.extractMonthAndYear(date);

        List<Optional<Expenses>> lst = repository.findExpensesByDate(id, date);

        return lst.stream().map(mapperExpenses::optionalToDto).collect(Collectors.toList());
    }

}
