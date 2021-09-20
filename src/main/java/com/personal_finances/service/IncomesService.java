package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.IncomesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Incomes;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.IncomesDTO;
import com.personal_finances.repository.RevenuesRepository;
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
public class IncomesService {

    private final RevenuesRepository repository;
    private final IncomesMapper mapperRevenue;

    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @Transactional
    public IncomesDTO save(IncomesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Incomes revenue = mapperRevenue.toRevenue(dto);
        repository.save(revenue);

        return mapperRevenue.toDto(revenue);
    }

    @Transactional
    public IncomesDTO update(IncomesDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Incomes revenue = mapperRevenue.toRevenue(dto);
        revenue.setId(dto.getId());

        repository.save(revenue);

        return mapperRevenue.toDto(revenue);
    }

    @Transactional
    public IncomesDTO delete(Long id){
        IncomesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public IncomesDTO findById(Long id){
        Optional<Incomes> optionalRevenue = repository.findById(id);

        if (optionalRevenue.isEmpty()) {
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return mapperRevenue.optionaltoDto(optionalRevenue);
    }

    @Transactional(readOnly = true)
    public List<IncomesDTO> findByCategory(Long id){
        List<Optional<Incomes>> optionalRevenue = repository.findByCategory(id);

        if (optionalRevenue.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return optionalRevenue.stream()
                .map(mapperRevenue::optionaltoDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<IncomesDTO> findAllRevenues(){

        List<IncomesDTO> lst = mapperRevenue.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

}
