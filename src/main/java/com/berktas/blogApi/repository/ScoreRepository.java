package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.Score;

import java.util.List;

public interface ScoreRepository extends BaseRepository<Score> {
    List<Score> findByPost(Post post);
}
