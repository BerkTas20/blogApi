package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.entity.Comment;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.User;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findByPostId(Long postId);
    boolean existsByDescriptionAndUser(String description, User user);
}
