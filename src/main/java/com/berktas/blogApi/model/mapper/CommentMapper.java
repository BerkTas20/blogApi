package com.berktas.blogApi.model.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.model.dto.CommentDto;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.Comment;
import com.berktas.blogApi.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<CommentDto, Comment> {
}
