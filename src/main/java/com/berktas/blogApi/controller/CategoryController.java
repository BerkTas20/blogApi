package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCategoryRequest;
import com.berktas.blogApi.dto.CategoryDto;
import com.berktas.blogApi.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> save(SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest){
        return ResponseEntity.ok(categoryService.save(saveAndUpdateCategoryRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest){
        return ResponseEntity.ok(categoryService.update(id, saveAndUpdateCategoryRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
