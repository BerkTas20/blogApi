package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateTagRequest;
import com.berktas.blogApi.dto.TagDto;

public interface TagService {
    TagDto save(SaveAndUpdateTagRequest saveAndUpdateTagRequest);

    TagDto getTag(Long id);

    TagDto updateTag(Long id, SaveAndUpdateTagRequest saveAndUpdateTagRequest);
}
