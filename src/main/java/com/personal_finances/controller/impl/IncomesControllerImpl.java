package com.personal_finances.controller.impl;

import com.personal_finances.controller.IncomesController;
import com.personal_finances.model.dto.IncomesDTO;
import com.personal_finances.service.IncomesService;
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
public class IncomesControllerImpl implements IncomesController {

    private final IncomesService serviceIncomes;

    @Override
    public ResponseEntity<IncomesDTO> save(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceIncomes.save(dto));
    }

    @Override
    public ResponseEntity<IncomesDTO> update(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceIncomes.update(dto));
    }

    @Override
    public ResponseEntity<IncomesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findById(id));
    }

    @Override
    public ResponseEntity<List<IncomesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findByCategory(id));
    }

    @Override
    public ResponseEntity<IncomesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.delete(id));
    }

    @Override
    public ResponseEntity<List<IncomesDTO>> findAllIncomes(){
        return ResponseEntity.ok(serviceIncomes.findAllIncomes());
    }

    @Override
    public ResponseEntity<List<IncomesDTO>> findIncomesByDate(
            @RequestParam(value = "date", defaultValue = "Incomes not found") String date,
            @PathVariable Long idAccount){
        return ResponseEntity.ok(serviceIncomes.findIncomesByDate(idAccount, date));
    }
}
