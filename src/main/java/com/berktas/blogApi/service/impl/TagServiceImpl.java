package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.dto.TagDto;
import com.berktas.blogApi.model.entity.Tag;
import com.berktas.blogApi.model.mapper.TagMapper;
import com.berktas.blogApi.repository.TagRepository;
import com.berktas.blogApi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private TagMapper tagMapper;

    @Override
    public TagDto save(Tag tag) {
        return tagMapper.entityToDto(tagRepository.save(tag));
    }

    @Override
    public TagDto getTag(Long id) {
        return tagMapper.entityToDto(tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found" + id)));
    }

    @Override
    public TagDto updateTag(Long id, Tag newTag) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found" + id));
        tag.setName(newTag.getName());
        return tagMapper.entityToDto(tagRepository.save(tag));
    }

    @Override
    public List<PostDto> getPostsByTag(String tagName) {
        return null;
    }


}
