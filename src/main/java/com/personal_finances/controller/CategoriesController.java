package com.personal_finances.controller;

import com.personal_finances.model.Categories;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesController {

    private final CategoriesService serviceCategory;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> save(@Valid @RequestBody CategoriesDTO dto){
        return ResponseEntity.ok(serviceCategory.save(dto));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.findById(id));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.delete(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDTO>> findAllCategories(){
        return ResponseEntity.ok(serviceCategory.findAllCategories());
    }
}
