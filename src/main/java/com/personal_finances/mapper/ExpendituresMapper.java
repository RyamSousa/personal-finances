package com.personal_finances.mapper;

import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.ExpendituresDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpendituresMapper {

    public ExpendituresDTO toDto(Expenditures expenditure) {
        ExpendituresDTO dto = new ExpendituresDTO();

        dto.setId(expenditure.getId());
        dto.setValue(expenditure.getValue());
        dto.setDate(expenditure.getDate());
        dto.setDescription(expenditure.getDescription());
        dto.setAccount(expenditure.getAccount());
        dto.setCategory(expenditure.getCategory());

        return dto;
    }

    public ExpendituresDTO optionaltoDto(Optional<Expenditures> expenditure) {
        ExpendituresDTO dto = new ExpendituresDTO();

        dto.setId(expenditure.get().getId());
        dto.setValue(expenditure.get().getValue());
        dto.setDate(expenditure.get().getDate());
        dto.setDescription(expenditure.get().getDescription());
        dto.setAccount(expenditure.get().getAccount());
        dto.setCategory(expenditure.get().getCategory());

        return dto;
    }

    public Expenditures toExpenditure(ExpendituresDTO dto) {
        Expenditures expenditure = new Expenditures();

        expenditure.setId(dto.getId());
        expenditure.setValue(dto.getValue());
        expenditure.setDate(dto.getDate());
        expenditure.setDescription(dto.getDescription());
        expenditure.setAccount(dto.getAccount());
        expenditure.setCategory(dto.getCategory());

        return expenditure;
    }

    public List<ExpendituresDTO> toListDTO(List<Expenditures> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
