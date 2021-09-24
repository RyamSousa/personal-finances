package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.repository.CategoriesRepository;
import com.personal_finances.utils.MessagesExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesService {

    private final CategoriesRepository repository;
    private final CategoriesMapper mapperCategory;

    public CategoriesDTO save(CategoriesDTO dto){
        Optional<Categories> optionalCategory = repository.findByName(dto.getName());

        if (optionalCategory.isPresent()){
            throw new BusinessException(MessagesExceptions.DATA_ALREADY_EXISTS);
        }

        Categories category = mapperCategory.toCategories(dto);
        repository.save(category);

        return mapperCategory.toDto(category);
    }

    public CategoriesDTO delete(Long id){
        CategoriesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    public CategoriesDTO findById(Long id){
        Optional<Categories> optionalCategory = repository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }

        return mapperCategory.optionaltoDto(optionalCategory);
    }

    public List<CategoriesDTO> findAllCategories(){

        List<CategoriesDTO> lst = mapperCategory.toListDTO(repository.findAll());

        if (lst.isEmpty()){
            throw new BusinessException(MessagesExceptions.NO_RECORDS_FOUND);
        }
        return lst;
    }
}
