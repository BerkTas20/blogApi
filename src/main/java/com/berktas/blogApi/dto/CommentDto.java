package com.berktas.blogApi.dto;

public record CommentDto(Long id,
                         String description,
                         Long userId,
                         String userName

) {
}
