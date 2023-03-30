package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SaveAndUpdateCommentRequest;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.model.dto.CommentDto;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.*;
import com.berktas.blogApi.model.mapper.CommentMapper;
import com.berktas.blogApi.model.mapper.PostMapper;
import com.berktas.blogApi.repository.*;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public CommentDto save(SaveAndUpdateCommentRequest commentRequest, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found" + postId));
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found" + userId));;
        //context teki user çağırılacak
        Comment comment = new Comment();
        comment.setDescription(commentRequest.getDescription());
        comment.setUser(user);
        comment.setPost(post);
        return commentMapper.entityToDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long id, SaveAndUpdateCommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Comment not found" + id));
        comment.setDescription(comment.getDescription());
        return commentMapper.entityToDto(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> new CommentDto(
                        comment.getId(),
                        comment.getDescription(),
                        comment.getUser().getId(),
                        comment.getUser().getUsername()
                ))
                .collect(Collectors.toList());
    }
}
