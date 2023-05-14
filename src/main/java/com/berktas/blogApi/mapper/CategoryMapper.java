package com.berktas.blogApi.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.dto.CategoryDto;
import com.berktas.blogApi.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDto, Category> {
}
