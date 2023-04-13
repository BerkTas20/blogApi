package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCategoryRequest;
import com.berktas.blogApi.model.dto.CategoryDto;
import com.berktas.blogApi.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public CategoryDto save(SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest){
        return categoryService.save(saveAndUpdateCategoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest){
        return categoryService.update(id, saveAndUpdateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
}
