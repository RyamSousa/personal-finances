package com.personal_finances.mapper;

import com.personal_finances.model.Incomes;
import com.personal_finances.model.dto.IncomesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IncomesMapper {

    public IncomesDTO toDto(Incomes income) {
        IncomesDTO dto = new IncomesDTO();

        dto.setId(income.getId());
        dto.setValue(income.getValue());
        dto.setDate(income.getDate());
        dto.setDescription(income.getDescription());
        dto.setAccount(income.getAccount());
        dto.setCategory(income.getCategory());

        return dto;
    }

    public IncomesDTO optionalToDto(Optional<Incomes> income) {
        IncomesDTO dto = new IncomesDTO();

        dto.setId(income.get().getId());
        dto.setValue(income.get().getValue());
        dto.setDate(income.get().getDate());
        dto.setDescription(income.get().getDescription());
        dto.setAccount(income.get().getAccount());
        dto.setCategory(income.get().getCategory());

        return dto;
    }

    public Incomes toRevenue(IncomesDTO dto) {
        Incomes income = new Incomes();

        income.setId(dto.getId());
        income.setValue(dto.getValue());
        income.setDate(dto.getDate());
        income.setDescription(dto.getDescription());
        income.setAccount(dto.getAccount());
        income.setCategory(dto.getCategory());

        return income;
    }

    public List<IncomesDTO> toListDTO(List<Incomes> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
