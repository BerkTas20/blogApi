package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {
    private final PostService postService;

    @PostMapping("/{userId}/categories/{categoryId}")
    public ResponseEntity<PostDto> save(SavePostRequest savePostRequest,
                                        @PathVariable Long userId,
                                        @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(postService.save(savePostRequest, userId, categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest updatePostRequest) {
        return ResponseEntity.ok(postService.update(id, updatePostRequest));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortBy,
            @RequestParam String sortDir
    ) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getOnePostById(id));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }

    @GetMapping("/{userId}")
    public List<PostDto> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        return new ResponseEntity<List<PostDto>>(postService.searchPosts(keywords), HttpStatus.OK);
    }


    @GetMapping("/tag/{id}")
    public ResponseEntity<PostResponse> getPostsByTag(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @PathVariable(name = "id") Long id) {
        PostResponse response = postService.getPostsByTag(id, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
