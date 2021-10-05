package com.personal_finances.controller;

import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.service.CategoriesService;
import com.personal_finances.service.RevenuesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/revenues")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RevenuesController {

    private final RevenuesService serviceRevenue;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> save(@Valid @RequestBody RevenuesDTO dto){
        return ResponseEntity.ok(serviceRevenue.save(dto));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findById(id));
    }

    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RevenuesDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.findByCategory(id));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenuesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceRevenue.delete(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RevenuesDTO>> findAllRevenues(){
        return ResponseEntity.ok(serviceRevenue.findAllRevenues());
    }
}
