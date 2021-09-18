package com.personal_finances.controller;

import com.personal_finances.model.dto.AccountsDTO;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.service.AccountsService;
import com.personal_finances.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsController {

    private final AccountsService serviceAccounts;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> save(@Valid @RequestBody AccountsDTO dto){
        return ResponseEntity.ok(serviceAccounts.save(dto));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.findById(id));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceAccounts.delete(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountsDTO>> findAllCategories(){
        return ResponseEntity.ok(serviceAccounts.findAllCategories());
    }
}
