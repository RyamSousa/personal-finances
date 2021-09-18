package com.personal_finances.mapper;

import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.RevenuesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RevenuesMapper {

    public RevenuesDTO toDto(Revenues revenue) {
        RevenuesDTO dto = new RevenuesDTO();

        dto.setId(revenue.getId());
        dto.setValue(revenue.getValue());
        dto.setDate(revenue.getDate());
        dto.setDescription(revenue.getDescription());
        dto.setAccount(revenue.getAccount());
        dto.setCategory(revenue.getCategory());

        return dto;
    }

    public RevenuesDTO optionaltoDto(Optional<Revenues> revenue) {
        RevenuesDTO dto = new RevenuesDTO();

        dto.setId(revenue.get().getId());
        dto.setValue(revenue.get().getValue());
        dto.setDate(revenue.get().getDate());
        dto.setDescription(revenue.get().getDescription());
        dto.setAccount(revenue.get().getAccount());
        dto.setCategory(revenue.get().getCategory());

        return dto;
    }

    public Revenues toRevenue(RevenuesDTO dto) {
        Revenues revenue = new Revenues();

        revenue.setId(dto.getId());
        revenue.setValue(dto.getValue());
        revenue.setDate(dto.getDate());
        revenue.setDescription(dto.getDescription());
        revenue.setAccount(dto.getAccount());
        revenue.setCategory(dto.getCategory());

        return revenue;
    }

    public List<RevenuesDTO> toListDTO(List<Revenues> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
