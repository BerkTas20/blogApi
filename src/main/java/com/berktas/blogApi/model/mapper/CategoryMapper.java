package com.berktas.blogApi.model.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.model.dto.CategoryDto;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.Category;
import com.berktas.blogApi.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDto, Category> {
}
