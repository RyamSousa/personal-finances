package com.personal_finances.controller;

import com.personal_finances.model.dto.ExpendituresDTO;
import com.personal_finances.model.dto.RevenuesDTO;
import com.personal_finances.service.ExpendituresService;
import com.personal_finances.service.RevenuesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/expenditures")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpendituresController {

    private final ExpendituresService serviceExpenditures;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpendituresDTO> save(@Valid @RequestBody ExpendituresDTO dto){
        return ResponseEntity.ok(serviceExpenditures.save(dto));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpendituresDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.findById(id));
    }

    @GetMapping(value = "/category/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpendituresDTO>> findByCategory(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.findByCategory(id));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpendituresDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceExpenditures.delete(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpendituresDTO>> findAllCategories(){
        return ResponseEntity.ok(serviceExpenditures.findAllRevenues());
    }
}
