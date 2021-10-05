package com.personal_finances.controller;

import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Retorna dados das categorias que estão relacionadas as receitas e despesas")
@RestController
@RequestMapping(value = "/api/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesController {

    private final CategoriesService serviceCategory;

    @ApiOperation("Cria uma nova categoria")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> save(@Valid @RequestBody CategoriesDTO dto){
        return ResponseEntity.ok(serviceCategory.save(dto));
    }

    @ApiOperation("Busca uma categoria por id\"")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.findById(id));
    }

    @ApiOperation("Deleta uma categoria por id")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.delete(id));
    }

    @ApiOperation("Deleta todas as categorias")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDTO>> findAllCategories(){
        return ResponseEntity.ok(serviceCategory.findAllCategories());
    }
}
