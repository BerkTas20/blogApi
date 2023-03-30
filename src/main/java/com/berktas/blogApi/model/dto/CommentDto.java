package com.berktas.blogApi.model.dto;

import com.berktas.blogApi.model.entity.Comment;

public record CommentDto(Long id,
                         String description,
                         Long userId,
                         String userName

) {
}
