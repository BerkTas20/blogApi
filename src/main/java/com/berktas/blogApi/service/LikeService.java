package com.berktas.blogApi.service;

import com.berktas.blogApi.dto.LikeDto;

public interface LikeService {
    LikeDto save(Long userId, Long postId);
    void delete(Long id);

}
