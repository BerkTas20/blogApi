package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCommentRequest;
import com.berktas.blogApi.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentDto save(SaveAndUpdateCommentRequest commentRequest, Long postId, Long userId);
    CommentDto updateComment(Long id, SaveAndUpdateCommentRequest commentRequest);
    void deleteComment(Long id);
    List<CommentDto> getAllComments(Optional<Long> userId, Optional<Long> postId);
}
