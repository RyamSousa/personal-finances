package com.personal_finances.controller.impl;

import com.personal_finances.controller.CategoriesController;
import com.personal_finances.model.Categories;
import com.personal_finances.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesControllerImpl implements CategoriesController {

    private final CategoriesService serviceCategory;

    @Override
    public ResponseEntity<Categories> save(@Valid @RequestBody Categories category){
        return ResponseEntity.ok(serviceCategory.save(category));
    }

    @Override
    public ResponseEntity<Categories> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.findById(id));
    }

    @Override
    public ResponseEntity<Categories> delete(@PathVariable Long id){
        return ResponseEntity.ok(serviceCategory.delete(id));
    }

    @Override
    public ResponseEntity<List<Categories>> findAllCategories(){
        return ResponseEntity.ok(serviceCategory.findAllCategories());
    }
}
