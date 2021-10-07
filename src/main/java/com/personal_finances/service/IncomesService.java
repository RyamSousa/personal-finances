package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.IncomesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Incomes;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.IncomesDTO;
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
public class IncomesService {

    private final IncomesRepository repository;
    private final IncomesMapper mapperIncome;

    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    public IncomesDTO save(IncomesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Incomes income = mapperIncome.toRevenue(dto);
        repository.save(income);

        return mapperIncome.toDto(income);
    }

    public IncomesDTO update(IncomesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Incomes income = mapperIncome.toRevenue(dto);
        income.setId(dto.getId());

        repository.save(income);

        return mapperIncome.toDto(income);
    }

    public IncomesDTO delete(Long id){
        IncomesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    public IncomesDTO findById(Long id){
        Optional<Incomes> optionalIncome = repository.findById(id);

        if (optionalIncome.isEmpty()) {
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return mapperIncome.optionalToDto(optionalIncome);
    }

    public List<IncomesDTO> findByCategory(Long id){
        List<Optional<Incomes>> optionalIncomes = repository.findByCategory(id);

        if (optionalIncomes.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalIncomes.stream()
                .map(mapperIncome::optionalToDto)
                .collect(Collectors.toList());
    }

    public List<IncomesDTO> findAllIncomes(){

        List<IncomesDTO> lst = mapperIncome.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<IncomesDTO> findIncomesByDate(Long id, String date){

        date = GetDate.extractMonthAndYear(date);

        List<Optional<Incomes>> lst = repository.findIncomesByDate(id, date);

        return lst.stream().map(mapperIncome::optionalToDto).collect(Collectors.toList());
    }

}
