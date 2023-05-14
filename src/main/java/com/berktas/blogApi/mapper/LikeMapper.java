package com.berktas.blogApi.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.dto.LikeDto;
import com.berktas.blogApi.model.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper extends BaseMapper<LikeDto, Like> {
}
