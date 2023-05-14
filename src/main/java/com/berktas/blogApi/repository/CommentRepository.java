package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.Comment;
import com.berktas.blogApi.model.User;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findByPostId(Long postId);
    boolean existsByDescriptionAndUser(String description, User user);
}
