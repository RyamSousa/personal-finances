package com.personal_finances.controller;

import com.personal_finances.model.dto.IncomesDTO;
import com.personal_finances.service.IncomesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das receitas")
@RestController
@RequestMapping(value = "/incomes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IncomesController {

    private final IncomesService serviceIncomes;

    @ApiOperation("Cria uma nova receita")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> save(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceIncomes.save(dto));
    }

    @ApiOperation("Atualiza uma receita")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> update(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceIncomes.update(dto));
    }

    @ApiOperation("Busca uma receita por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findById(id));
    }

    @ApiOperation("Busca uma receita por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.findByCategory(id));
    }

    @ApiOperation("Deleta uma receita por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceIncomes.delete(id));
    }

    @ApiOperation("Busca todas as receitas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findAllIncomes(){
        return ResponseEntity.ok(serviceIncomes.findAllIncomes());
    }

    @ApiOperation("Busca todas as receitas por mês e ano")
    @GetMapping(value = "/date/{idAccount}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findIncomesByDate(
            @RequestParam(value = "date", defaultValue = "Incomes not found") String date,
            @PathVariable Long idAccount){
        return ResponseEntity.ok(serviceIncomes.findIncomesByDate(idAccount, date));
    }
}
