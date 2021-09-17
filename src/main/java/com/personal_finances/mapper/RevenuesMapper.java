package com.personal_finances.mapper;

import com.personal_finances.model.Revenues;
import com.personal_finances.model.dto.RevenuesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RevenuesMapper {
    public RevenuesDTO toDto(Revenues revenues) {
        RevenuesDTO dto = new RevenuesDTO();

        dto.setId(revenues.getId());
        dto.setValue(revenues.getValue());
        dto.setDate(revenues.getDate());
        dto.setDescription(revenues.getDescription());
        dto.setAccount(revenues.getAccount());
        dto.setCategory(revenues.getCategory());

        return dto;
    }

    public RevenuesDTO optionaltoDto(Optional<Revenues> revenues) {
        RevenuesDTO dto = new RevenuesDTO();

        dto.setId(revenues.get().getId());
        dto.setValue(revenues.get().getValue());
        dto.setDate(revenues.get().getDate());
        dto.setDescription(revenues.get().getDescription());
        dto.setAccount(revenues.get().getAccount());
        dto.setCategory(revenues.get().getCategory());

        return dto;
    }

    public Revenues toCategories(RevenuesDTO dto) {
        Revenues revenues = new Revenues();

        revenues.setId(dto.getId());
        revenues.setValue(dto.getValue());
        revenues.setDate(dto.getDate());
        revenues.setDescription(dto.getDescription());
        revenues.setAccount(dto.getAccount());
        revenues.setCategory(dto.getCategory());

        return revenues;
    }


    public List<RevenuesDTO> toListDTO(List<Revenues> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
