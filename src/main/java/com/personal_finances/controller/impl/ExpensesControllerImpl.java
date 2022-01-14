package com.personal_finances.controller.impl;

import com.personal_finances.controller.ExpensesController;
import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.service.ExpensesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class ExpensesControllerImpl implements ExpensesController {

    private final ExpensesService serviceExpenses;

    @Override
    public ResponseEntity<ExpensesDTO> save(@Valid @RequestBody ExpensesDTO dto){
        return ResponseEntity.ok(serviceExpenses.save(dto));
    }

    @Override
    public ResponseEntity<ExpensesDTO> update(@Valid @RequestBody ExpensesDTO dto){
        return ResponseEntity.ok(serviceExpenses.update(dto));
    }

    @Override
    public ResponseEntity<ExpensesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.findById(id));
    }

    @Override
    public ResponseEntity<List<ExpensesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.findByCategory(id));
    }

    @Override
    public ResponseEntity<ExpensesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.delete(id));
    }

    @Override
    public ResponseEntity<List<ExpensesDTO>> findAllExpenditures(){
        return ResponseEntity.ok(serviceExpenses.findAllExpenses());
    }

    @Override
    public ResponseEntity<List<ExpensesDTO>> findExpensesByDate(
            @RequestParam(value = "date", defaultValue = "Expenses not found") String date,
            @PathVariable Long idAccount){
        return ResponseEntity.ok(serviceExpenses.findExpensesByDate(idAccount, date));
    }
}
