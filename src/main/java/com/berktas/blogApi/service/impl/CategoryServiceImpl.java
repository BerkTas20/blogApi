package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCategoryRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.dto.CategoryDto;
import com.berktas.blogApi.model.Category;
import com.berktas.blogApi.mapper.CategoryMapper;
import com.berktas.blogApi.repository.CategoryRepository;
import com.berktas.blogApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest) {
        Category category = new Category();
        category.setTitle(saveAndUpdateCategoryRequest.getTitle());
        category.setDescription(saveAndUpdateCategoryRequest.getDescription());
        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, SaveAndUpdateCategoryRequest saveAndUpdateCategoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found" + id));
        category.setTitle(saveAndUpdateCategoryRequest.getTitle());
        category.setDescription(saveAndUpdateCategoryRequest.getDescription());
        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catgory not found" + id));
        return categoryMapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.entityListToDtoList(categoryRepository.findAll());
    }
}
