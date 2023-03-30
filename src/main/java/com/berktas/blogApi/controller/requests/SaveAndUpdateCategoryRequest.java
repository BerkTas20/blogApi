package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.model.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAndUpdateCategoryRequest {
  CategoryDto categoryDto;
}
