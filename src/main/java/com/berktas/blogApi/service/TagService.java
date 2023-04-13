package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateTagRequest;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.dto.TagDto;
import com.berktas.blogApi.model.entity.Tag;

import java.util.List;

public interface TagService {
    TagDto save(SaveAndUpdateTagRequest saveAndUpdateTagRequest);

    TagDto getTag(Long id);

    TagDto updateTag(Long id, SaveAndUpdateTagRequest saveAndUpdateTagRequest);
}
