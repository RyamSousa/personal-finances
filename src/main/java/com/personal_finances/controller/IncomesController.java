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
@RequestMapping(value = "/revenues")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IncomesController {

    private final IncomesService serviceRevenue;

    @ApiOperation("Cria uma nova receita")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> save(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceRevenue.save(dto));
    }

    @ApiOperation("Atualiza uma receita")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> update(@Valid @RequestBody IncomesDTO dto){
        return ResponseEntity.ok(serviceRevenue.update(dto));
    }

    @ApiOperation("Busca uma receita por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findById(id));
    }

    @ApiOperation("Busca uma receita por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findByCategory(id));
    }

    @ApiOperation("Deleta uma receita por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.delete(id));
    }

    @ApiOperation("Busca todas as receitas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomesDTO>> findAllRevenues(){
        return ResponseEntity.ok(serviceRevenue.findAllRevenues());
    }
}
