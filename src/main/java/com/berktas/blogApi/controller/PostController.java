package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.dto.PostDto;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {
    private final PostService postService;

    @PostMapping("/{userId}/categories/{categoryId}")
    @Operation(summary = "Create Post")
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

    @GetMapping
    @Operation(summary = "Get All Posts With Pagination And Sorting Features")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getOnePostById(id));
    }

    @GetMapping("/getPostsByUser")
    @Operation(summary = "Get Posts By User")
    public List<PostDto> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping("/search/{keywords}")
    @Operation(summary = "Search Post By Title")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        return new ResponseEntity<List<PostDto>>(postService.searchPosts(keywords), HttpStatus.OK);
    }

    @GetMapping("/recent/{userId}")
    @Operation(summary = "Last 5 Posts By User")
    public ResponseEntity<List<Post>> getUserRecentPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getLatestPostsByUser(userId));
    }

    @PostMapping("/posts/{postId}/view")
    @Operation(summary = "Viewing The Post")
    public ResponseEntity<String> viewPost(@PathVariable Long postId) {
        postService.incrementViews(postId);
        return ResponseEntity.ok("Post viewed successfully");
    }

    @PostMapping("/{postId}/{userId}/rating")
    @Operation(summary = "Scoring The Post")
    public ResponseEntity<String> ratePost(@PathVariable Long postId, @PathVariable Long userId, @RequestParam double rating) {
        postService.ratePost(postId, userId, rating);
        return ResponseEntity.ok("Post rating saved successfully.");
    }

    @GetMapping("/category/{categoryName}")
    @Operation(summary = "Filtering Posts By Category")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryName));
    }
}
