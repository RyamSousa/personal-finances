package com.personal_finances.service;

import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.repository.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesService {

    private final CategoriesRepository repository;
    private final CategoriesMapper mapperCategory;

    @Transactional
    public CategoriesDTO save(CategoriesDTO dto){
        Optional<Categories> optionalCategory = repository.findByName(dto.getName());

        if (optionalCategory.isPresent()){
            System.out.println("Lançar exceção");
        }

        Categories category = mapperCategory.toCategories(dto);
        repository.save(category);

        return mapperCategory.toDto(category);
    }

    @Transactional
    public CategoriesDTO delete(Long id){
        CategoriesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public CategoriesDTO findById(Long id){
        Optional<Categories> optionalCategory = repository.findById(id);

        if (optionalCategory.isEmpty()) {
            System.out.println("Lançar exceção");
        }
        return mapperCategory.optionaltoDto(optionalCategory);
    }

    @Transactional(readOnly = true)
    public List<CategoriesDTO> findAllCategories(){
        return mapperCategory.toListDTO(repository.findAll());
    }
}
