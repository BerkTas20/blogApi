package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.dto.PostDto;
import com.berktas.blogApi.model.Post;

import java.util.List;

public interface PostService {
    PostDto save(SavePostRequest savePostRequest, Long userId, Long categoryId);

    PostDto update(Long id, UpdatePostRequest updatePostRequest);

    void delete(Long id);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto getOnePostById(Long postId);

    List<Post> getPostsByCategory(String categoryName);

    List<PostDto> getPostsByUser(Long userId);

    List<PostDto> searchPosts(String keyword);

    List<Post> getLatestPostsByUser(Long userId);

    void incrementViews(Long postId);

    void ratePost(Long postId, Long userId, double rating);


}
