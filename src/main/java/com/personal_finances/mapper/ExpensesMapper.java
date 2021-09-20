package com.personal_finances.mapper;

import com.personal_finances.model.Expenses;
import com.personal_finances.model.dto.ExpensesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpensesMapper {

    public ExpensesDTO toDto(Expenses expenditure) {
        ExpensesDTO dto = new ExpensesDTO();

        dto.setId(expenditure.getId());
        dto.setValue(expenditure.getValue());
        dto.setDate(expenditure.getDate());
        dto.setDescription(expenditure.getDescription());
        dto.setAccount(expenditure.getAccount());
        dto.setCategory(expenditure.getCategory());

        return dto;
    }

    public ExpensesDTO optionaltoDto(Optional<Expenses> expenditure) {
        ExpensesDTO dto = new ExpensesDTO();

        dto.setId(expenditure.get().getId());
        dto.setValue(expenditure.get().getValue());
        dto.setDate(expenditure.get().getDate());
        dto.setDescription(expenditure.get().getDescription());
        dto.setAccount(expenditure.get().getAccount());
        dto.setCategory(expenditure.get().getCategory());

        return dto;
    }

    public Expenses toExpenditure(ExpensesDTO dto) {
        Expenses expenditure = new Expenses();

        expenditure.setId(dto.getId());
        expenditure.setValue(dto.getValue());
        expenditure.setDate(dto.getDate());
        expenditure.setDescription(dto.getDescription());
        expenditure.setAccount(dto.getAccount());
        expenditure.setCategory(dto.getCategory());

        return expenditure;
    }

    public List<ExpensesDTO> toListDTO(List<Expenses> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
