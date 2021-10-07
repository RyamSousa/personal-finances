package com.personal_finances.mapper;

import com.personal_finances.model.Expenses;
import com.personal_finances.model.dto.ExpensesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpensesMapper {

    public ExpensesDTO toDto(Expenses expense) {
        ExpensesDTO dto = new ExpensesDTO();

        dto.setId(expense.getId());
        dto.setValue(expense.getValue());
        dto.setDate(expense.getDate());
        dto.setDescription(expense.getDescription());
        dto.setAccount(expense.getAccount());
        dto.setCategory(expense.getCategory());

        return dto;
    }

    public ExpensesDTO optionalToDto(Optional<Expenses> expense) {
        ExpensesDTO dto = new ExpensesDTO();

        dto.setId(expense.get().getId());
        dto.setValue(expense.get().getValue());
        dto.setDate(expense.get().getDate());
        dto.setDescription(expense.get().getDescription());
        dto.setAccount(expense.get().getAccount());
        dto.setCategory(expense.get().getCategory());

        return dto;
    }

    public Expenses toExpenditure(ExpensesDTO dto) {
        Expenses expense = new Expenses();

        expense.setId(dto.getId());
        expense.setValue(dto.getValue());
        expense.setDate(dto.getDate());
        expense.setDescription(dto.getDescription());
        expense.setAccount(dto.getAccount());
        expense.setCategory(dto.getCategory());

        return expense;
    }

    public List<ExpensesDTO> toListDTO(List<Expenses> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
