package com.personal_finances.service;

import com.personal_finances.mapper.CategoriesMapper;
import com.personal_finances.mapper.ExpendituresMapper;
import com.personal_finances.mapper.RevenuesMapper;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.ExpendituresDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.repository.ExpendituresRepository;
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
public class ExpendituresService {

    private final ExpendituresRepository repository;
    private final ExpendituresMapper mapperExpenditure;
    private final CategoriesService categoriesService;
    private final CategoriesMapper categoriesMapper;

    @Transactional
    public ExpendituresDTO save(ExpendituresDTO dto){

        Categories category = categoriesMapper.toCategories(
                categoriesService.findById(dto.getCategory().getId())
        );

        dto.setCategory(category);
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
            System.out.println("Lançar exceção");
        }

        return mapperExpenditure.optionaltoDto(optionalExpenditure);
    }

    @Transactional(readOnly = true)
    public List<ExpendituresDTO> findByCategory(Long id){
        List<Optional<Expenditures>> optionalExpenditure = repository.findByCategory(id);

        if (optionalExpenditure.isEmpty()){
            System.out.println("Lançar exceção");
        }
        List<ExpendituresDTO> dtos = optionalExpenditure.stream()
                .map(mapperExpenditure::optionaltoDto)
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional(readOnly = true)
    public List<ExpendituresDTO> findAllExpenditures(){
        return mapperExpenditure.toListDTO(repository.findAll());
    }

}
