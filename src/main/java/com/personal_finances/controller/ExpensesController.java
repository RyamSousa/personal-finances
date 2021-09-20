package com.personal_finances.controller;

import com.personal_finances.model.dto.ExpensesDTO;
import com.personal_finances.service.ExpensesService;
import com.personal_finances.utils.GetDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das despesas")
@RestController
@RequestMapping(value = "/expenses")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpensesController {

    private final ExpensesService serviceExpenditures;

    @ApiOperation("Cria uma nova despesa")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpensesDTO> save(@Valid @RequestBody ExpensesDTO dto){
        return ResponseEntity.ok(serviceExpenditures.save(dto));
    }

    @ApiOperation("Atualiza uma despesa")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpensesDTO> update(@Valid @RequestBody ExpensesDTO dto){
        return ResponseEntity.ok(serviceExpenditures.update(dto));
    }

    @ApiOperation("Busca uma despesa por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpensesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.findById(id));
    }

    @ApiOperation("Busca uma despesa por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpensesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.findByCategory(id));
    }

    @ApiOperation("Deleta uma despesa por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpensesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.delete(id));
    }

    @ApiOperation("Busca todas as despesas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpensesDTO>> findAllExpenditures(){
        return ResponseEntity.ok(serviceExpenditures.findAllExpenses());
    }

    @ApiOperation("Busca todas as despesas por mês e ano")
    @GetMapping(value = "/date/{idAccount}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpensesDTO>> findExpensesByDate(
            @RequestParam(value = "date", defaultValue = "Expenses not found") String date,
            @PathVariable Long idAccount){
        System.out.println(date);
        System.out.println(GetDate.extractMonthAndYear(date));
        System.out.println(idAccount);
        return ResponseEntity.ok(serviceExpenditures.findExpensesByDate(date, idAccount));
    }
}
