package com.personal_finances.controller.impl;

import com.personal_finances.controller.IncomesController;
import com.personal_finances.model.Incomes;
import com.personal_finances.service.IncomesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomesControllerImpl implements IncomesController {

    private final IncomesService serviceIncomes;

    @Override
    public ResponseEntity<Incomes> save(@Valid @RequestBody Incomes income){
        return ResponseEntity.ok(serviceIncomes.save(income));
    }

    @Override
    public ResponseEntity<Incomes> update(@Valid @RequestBody Incomes income){
        return ResponseEntity.ok(serviceIncomes.update(income));
    }

    @Override
    public ResponseEntity<Incomes> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findById(id));
    }

    @Override
    public ResponseEntity<List<Incomes>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findByCategory(id));
    }

    @Override
    public ResponseEntity<Incomes> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.delete(id));
    }

    @Override
    public ResponseEntity<List<Incomes>> findAllIncomes(){
        return ResponseEntity.ok(serviceIncomes.findAllIncomes());
    }

    @Override
    public ResponseEntity<List<Incomes>> findIncomesByDate(
            @RequestParam(value = "date", defaultValue = "Incomes not found") String date,
            @PathVariable Long idAccount){
        return ResponseEntity.ok(serviceIncomes.findIncomesByDate(idAccount, date));
    }
}
