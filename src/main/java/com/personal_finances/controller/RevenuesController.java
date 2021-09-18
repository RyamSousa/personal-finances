package com.personal_finances.controller;

import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.service.CategoriesService;
import com.personal_finances.service.RevenuesService;
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
public class RevenuesController {

    private final RevenuesService serviceRevenue;

    @ApiOperation("Cria uma nova receita")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> save(@Valid @RequestBody RevenuesDTO dto){
        return ResponseEntity.ok(serviceRevenue.save(dto));
    }

    @ApiOperation("Busca uma receita por id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findById(id));
    }

    @ApiOperation("Busca uma receita por categoria")
    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RevenuesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findByCategory(id));
    }

    @ApiOperation("Deleta uma receita por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.delete(id));
    }

    @ApiOperation("Busca todas as receitas")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RevenuesDTO>> findAllRevenues(){
        return ResponseEntity.ok(serviceRevenue.findAllRevenues());
    }
}
