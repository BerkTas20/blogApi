package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCommentRequest;
import com.berktas.blogApi.dto.CommentDto;
import com.berktas.blogApi.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "Comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> save(SaveAndUpdateCommentRequest commentRequest,
                                           @RequestParam Long userId,
                                           @RequestParam Long postId
    ) {
        return ResponseEntity.ok(commentService.save(commentRequest, userId, postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updatePost(@PathVariable Long id, @RequestBody SaveAndUpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.ok(commentService.updateComment(id, updateCommentRequest));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        commentService.deleteComment(id);
    }


    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return ResponseEntity.ok(commentService.getAllComments(userId, postId));
    }


}
