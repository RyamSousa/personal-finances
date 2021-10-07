package com.personal_finances.mapper;

import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class CategoriesMapper{

    public CategoriesDTO toDto(Categories categories) {
        CategoriesDTO dto = new CategoriesDTO();

        dto.setId(categories.getId());
        dto.setName(categories.getName());

        return dto;
    }

    public CategoriesDTO optionaltoDto(Optional<Categories> categories) {
        CategoriesDTO dto = new CategoriesDTO();

        dto.setId(categories.get().getId());
        dto.setName(categories.get().getName());

        return dto;
    }

    public Categories toCategories(CategoriesDTO dto) {
        Categories categories = new Categories();

        categories.setId(dto.getId());
        categories.setName(dto.getName());

        return categories;
    }

    public List<CategoriesDTO> toListDTO(List<Categories> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
