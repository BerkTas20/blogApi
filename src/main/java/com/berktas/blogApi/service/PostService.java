package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.model.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto save(SavePostRequest savePostRequest, Long userId, Long categoryId);
    PostDto update(Long id, UpdatePostRequest updatePostRequest);
    void delete(Long id);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PostDto getOnePostById(Long postId);
    List<PostDto> getPostsByCategory(Long categoryId);
    //get all posts by user
    List<PostDto> getPostsByUser(Long userId);
    //search posts
    List<PostDto> searchPosts(String keyword);
}
