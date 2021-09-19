package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.AccountMapper;
import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.ExpendituresMapper;
import com.personal_finances.mapper.RevenuesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.ExpendituresDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.repository.ExpendituresRepository;
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
public class ExpendituresService {

    private final ExpendituresRepository repository;
    private final ExpendituresMapper mapperExpenditure;

    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @Transactional
    public ExpendituresDTO save(ExpendituresDTO dto){

        AccountsDTO account = accountsService.findByAccountNumber(dto.getAccount().getAccountNumber());

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        dto.setAccount(accountMapper.toAccounts(account));

        Expenditures expenditure = mapperExpenditure.toExpenditure(dto);
        repository.save(expenditure);

        return mapperExpenditure.toDto(expenditure);
    }

    @Transactional
    public ExpendituresDTO delete(Long id){
        ExpendituresDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public ExpendituresDTO findById(Long id){
        Optional<Expenditures> optionalExpenditure = repository.findById(id);

        if (optionalExpenditure.isEmpty()) {
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return mapperExpenditure.optionaltoDto(optionalExpenditure);
    }

    @Transactional(readOnly = true)
    public List<ExpendituresDTO> findByCategory(Long id){
        List<Optional<Expenditures>> optionalExpenditure = repository.findByCategory(id);

        if (optionalExpenditure.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return optionalExpenditure.stream()
                .map(mapperExpenditure::optionaltoDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ExpendituresDTO> findAllExpenditures(){
        List<ExpendituresDTO> lst = mapperExpenditure.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return lst;
    }

}
