package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.entity.Category;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.Tag;
import com.berktas.blogApi.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends BaseRepository<Post> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

//    Page<Post> findByTagsIn(List<Tag> tags, Pageable pageable);
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String key);
}