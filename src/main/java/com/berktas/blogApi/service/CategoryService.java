package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCategoryRequest;
import com.berktas.blogApi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest);
    CategoryDto update(Long id, SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest);
    void delete(Long id);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();

}
