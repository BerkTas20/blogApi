package com.berktas.blogApi.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.dto.CommentDto;
import com.berktas.blogApi.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<CommentDto, Comment> {
}
