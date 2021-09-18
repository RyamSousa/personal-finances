package com.personal_finances.service;

import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.RevenuesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.repository.RevenuesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RevenuesService {

    private final RevenuesRepository repository;
    private final RevenuesMapper mapperRevenue;
    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    @Transactional
    public RevenuesDTO save(RevenuesDTO dto){

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
        Revenues revenue = mapperRevenue.toRevenue(dto);
        repository.save(revenue);

        return mapperRevenue.toDto(revenue);
    }

    @Transactional
    public RevenuesDTO delete(Long id){
        RevenuesDTO dto = this.findById(id);

        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public RevenuesDTO findById(Long id){
        Optional<Revenues> optionalRevenue = repository.findById(id);

        if (optionalRevenue.isEmpty()) {
            System.out.println("Lançar exceção");
        }

        return mapperRevenue.optionaltoDto(optionalRevenue);
    }

    @Transactional(readOnly = true)
    public List<RevenuesDTO> findByCategory(Long id){
        List<Optional<Revenues>> optionalRevenue = repository.findByCategory(id);

        if (optionalRevenue.isEmpty()){
            System.out.println("Lançar exceção");
        }
        List<RevenuesDTO> dtos = optionalRevenue.stream()
                .map(mapperRevenue::optionaltoDto)
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional(readOnly = true)
    public List<RevenuesDTO> findAllRevenues(){
        return mapperRevenue.toListDTO(repository.findAll());
    }

}
