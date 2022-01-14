package com.personal_finances.controller.impl;

import com.personal_finances.controller.CategoriesController;
import com.personal_finances.model.dto.CategoriesDTO;
import com.personal_finances.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class CategoriesControllerImpl implements CategoriesController {

    private final CategoriesService serviceCategory;

    @Override
    public ResponseEntity<CategoriesDTO> save(@Valid @RequestBody CategoriesDTO dto){
        return ResponseEntity.ok(serviceCategory.save(dto));
    }

    @Override
    public ResponseEntity<CategoriesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.findById(id));
    }

    @Override
    public ResponseEntity<CategoriesDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.delete(id));
    }

    @Override
    public ResponseEntity<List<CategoriesDTO>> findAllCategories(){
        return ResponseEntity.ok(serviceCategory.findAllCategories());
    }
}
