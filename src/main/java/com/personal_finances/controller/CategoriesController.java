package com.personal_finances.controller;

import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.service.CategoriesService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(value = "/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriesController {

    private final CategoriesService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> save(@Valid @RequestBody CategoriesDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoriesDTO>> findAllCategories(){
        return ResponseEntity.ok(service.findAllCategories());
    }
}
