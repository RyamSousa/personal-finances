package com.personal_finances.controller;

import com.personal_finances.model.Incomes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das receitas")
@RestController
@RequestMapping(value = "/api/incomes")
public interface IncomesController {

    @ApiOperation("Cria uma nova receita")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Incomes> save(@Valid @RequestBody Incomes income);

    @ApiOperation("Atualiza uma receita")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Incomes> update(@Valid @RequestBody Incomes income);

    @ApiOperation("Busca uma receita por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Incomes> findById(@PathVariable Long id);

    @ApiOperation("Busca uma receita por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Incomes>> findByCategory(@PathVariable Long id);

    @ApiOperation("Deleta uma receita por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Incomes> delete(@PathVariable Long id);

    @ApiOperation("Busca todas as receitas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Incomes>> findAllIncomes();

    @ApiOperation("Busca todas as receitas por mês e ano")
    @GetMapping(value = "/date/{idAccount}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Incomes>> findIncomesByDate(
            @RequestParam(value = "date", defaultValue = "Incomes not found") String date,
            @PathVariable Long idAccount);
}
