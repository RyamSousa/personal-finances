package com.personal_finances.mapper;

import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


// ESTUDAR API MAPPER


@Component
public class CategoriesMapper {

    public Categories toCategory(CategoriesDTO dto){
        Categories category = new Categories();

        category.setId(dto.getId());
        category.setName(dto.getName());

        return category;
    }

    public CategoriesDTO toCategoryDTO(Categories category){
        CategoriesDTO dto = new CategoriesDTO();

        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }

    public Set<CategoriesDTO> toListCategories(Set<Categories> listCategories){
        return listCategories.stream().map(this::toCategoryDTO).collect(Collectors.toSet());
    }
}
