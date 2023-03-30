package com.berktas.blogApi.model.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.model.dto.LikeDto;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.Like;
import com.berktas.blogApi.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper extends BaseMapper<LikeDto, Like> {
}
