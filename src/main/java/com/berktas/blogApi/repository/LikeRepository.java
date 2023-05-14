package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.Like;

public interface LikeRepository extends BaseRepository<Like> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
