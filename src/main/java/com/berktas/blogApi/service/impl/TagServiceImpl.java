package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.SaveAndUpdateTagRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.dto.TagDto;
import com.berktas.blogApi.model.Tag;
import com.berktas.blogApi.mapper.TagMapper;
import com.berktas.blogApi.repository.TagRepository;
import com.berktas.blogApi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;


    @Override
    public TagDto save(SaveAndUpdateTagRequest saveAndUpdateTagRequest) {
        Tag tag = new Tag();
        tag.setName(saveAndUpdateTagRequest.getName());
        return tagMapper.entityToDto(tagRepository.save(tag));
    }

    @Override
    public TagDto getTag(Long id) {
        return tagMapper.entityToDto(tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found" + id)));
    }

    @Override
    public TagDto updateTag(Long id, SaveAndUpdateTagRequest saveAndUpdateTagRequest) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found" + id));
        tag.setName(saveAndUpdateTagRequest.getName());
        return tagMapper.entityToDto(tagRepository.save(tag));
    }

}




