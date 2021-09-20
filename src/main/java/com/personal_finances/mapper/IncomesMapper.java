package com.personal_finances.mapper;

import com.personal_finances.model.Incomes;
import com.personal_finances.model.dto.IncomesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IncomesMapper {

    public IncomesDTO toDto(Incomes revenue) {
        IncomesDTO dto = new IncomesDTO();

        dto.setId(revenue.getId());
        dto.setValue(revenue.getValue());
        dto.setDate(revenue.getDate());
        dto.setDescription(revenue.getDescription());
        dto.setAccount(revenue.getAccount());
        dto.setCategory(revenue.getCategory());

        return dto;
    }

    public IncomesDTO optionaltoDto(Optional<Incomes> revenue) {
        IncomesDTO dto = new IncomesDTO();

        dto.setId(revenue.get().getId());
        dto.setValue(revenue.get().getValue());
        dto.setDate(revenue.get().getDate());
        dto.setDescription(revenue.get().getDescription());
        dto.setAccount(revenue.get().getAccount());
        dto.setCategory(revenue.get().getCategory());

        return dto;
    }

    public Incomes toRevenue(IncomesDTO dto) {
        Incomes revenue = new Incomes();

        revenue.setId(dto.getId());
        revenue.setValue(dto.getValue());
        revenue.setDate(dto.getDate());
        revenue.setDescription(dto.getDescription());
        revenue.setAccount(dto.getAccount());
        revenue.setCategory(dto.getCategory());

        return revenue;
    }

    public List<IncomesDTO> toListDTO(List<Incomes> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
