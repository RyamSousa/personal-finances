package com.personal_finances.service;

import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.repository.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesService {

    private final CategoriesRepository repository;
    private final CategoriesMapper mapper;

    @Transactional
    public CategoriesDTO save(CategoriesDTO dto){
        Optional<Categories> OptionalCategory = repository.findById(dto.getId());

        if (OptionalCategory.isPresent()){
            System.out.println("Lançar exceção");
        }

        Categories category = mapper.toCategory(dto);
        repository.save(category);

        return mapper.toCategoryDTO(category);
    }

    public Set<CategoriesDTO> findAllCategories(){
        return mapper.toListCategories((Set<Categories>) repository.findAll());
    }
}
