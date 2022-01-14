package com.personal_finances.controller;

import com.personal_finances.model.Expenses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das despesas")
@RestController
@RequestMapping(value = "/api/expenses")
public interface ExpensesController {

    @ApiOperation("Cria uma nova despesa")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> save(@Valid @RequestBody Expenses expense);

    @ApiOperation("Atualiza uma despesa")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> update(@Valid @RequestBody Expenses expense);

    @ApiOperation("Busca uma despesa por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> findById(@PathVariable Long id);

    @ApiOperation("Busca uma despesa por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> findByCategory(@PathVariable Long id);

    @ApiOperation("Deleta uma despesa por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> delete(@PathVariable Long id);

    @ApiOperation("Busca todas as despesas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> findAllExpenditures();

    @ApiOperation("Busca todas as despesas por mês e ano")
    @GetMapping(value = "/date/{idAccount}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> findExpensesByDate(
            @RequestParam(value = "date", defaultValue = "Expenses not found") String date,
            @PathVariable Long idAccount);
}
