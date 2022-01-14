package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Categories;
import com.personal_finances.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.personal_finances.utils.MessagesExceptions.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public Categories save(Categories category){
        Optional<Categories> optionalCategory = categoriesRepository.findByName(category.getName());

        if (optionalCategory.isPresent()){
            throw new BusinessException(DATA_ALREADY_EXISTS);
        }

        categoriesRepository.save(category);

        return category;
    }

    public Categories delete(Long id){
        Categories category = this.findById(id);

        categoriesRepository.deleteById(id);

        return category;
    }

    public Categories findById(Long id){
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalCategory.get();
    }

    public List<Categories> findAllCategories(){

        List<Categories> lst = categoriesRepository.findAll();

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }
        return lst;
    }
}
