package com.berktas.blogApi.model.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.dto.TagDto;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDto, Tag> {
}
