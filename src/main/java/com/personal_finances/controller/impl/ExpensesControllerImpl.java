package com.personal_finances.controller.impl;

import com.personal_finances.controller.ExpensesController;
import com.personal_finances.model.Expenses;
import com.personal_finances.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpensesControllerImpl implements ExpensesController {

    private final ExpensesService serviceExpenses;

    @Override
    public ResponseEntity<Expenses> save(@Valid @RequestBody Expenses expense){
        return ResponseEntity.ok(serviceExpenses.save(expense));
    }

    @Override
    public ResponseEntity<Expenses> update(@Valid @RequestBody Expenses expense){
        return ResponseEntity.ok(serviceExpenses.update(expense));
    }

    @Override
    public ResponseEntity<Expenses> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.findById(id));
    }

    @Override
    public ResponseEntity<List<Expenses>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.findByCategory(id));
    }

    @Override
    public ResponseEntity<Expenses> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenses.delete(id));
    }

    @Override
    public ResponseEntity<List<Expenses>> findAllExpenditures(){
        return ResponseEntity.ok(serviceExpenses.findAllExpenses());
    }

    @Override
    public ResponseEntity<List<Expenses>> findExpensesByDate(
            @RequestParam(value = "date", defaultValue = "Expenses not found") String date,
            @PathVariable Long idAccount){
        return ResponseEntity.ok(serviceExpenses.findExpensesByDate(idAccount, date));
    }
}
