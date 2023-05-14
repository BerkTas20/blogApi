package com.berktas.blogApi.repository;

import cz.jirutka.rsql.parser.ast.Node;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.Category;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends BaseRepository<Post>, JpaSpecificationExecutor<Post> {
    List<Post> findByUser(User user);

//    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String key);

    List<Post> findTop5ByUserOrderByCreatedDateTimeDesc(User user);


}