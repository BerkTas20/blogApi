package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.dto.LikeDto;
import com.berktas.blogApi.model.Like;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.mapper.LikeMapper;
import com.berktas.blogApi.repository.LikeRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.UserRepository;
import com.berktas.blogApi.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeMapper likeMapper;

    @Override
    public LikeDto save(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new RuntimeException("User already liked this post");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        Like like = new Like(user, post);
        return likeMapper.entityToDto(likeRepository.save(like));
    }

    @Override
    public void delete(Long id) {
        likeRepository.deleteById(id);
    }
}
