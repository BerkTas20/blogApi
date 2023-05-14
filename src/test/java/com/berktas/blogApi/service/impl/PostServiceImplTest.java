package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.dto.PostDto;
import com.berktas.blogApi.model.Category;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.mapper.PostMapper;
import com.berktas.blogApi.repository.CategoryRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {


    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostMapper postMapper;

    @Test
    public void shouldSavePostSuccessfully() {
        Long userId = 1L;
        Long categoryId = 2L;

        User user = new User();
        user.setId(userId);

        Category category = new Category();
        category.setId(categoryId);

        generateCreatePost();

        Post savedPost = new Post();
        savedPost.setId(1L);
        savedPost.setTitle(generateCreatePost().getTitle());
        savedPost.setDescription(generateCreatePost().getDescription());
        savedPost.setUser(user);
        savedPost.setCategory(category);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(savedPost);
        PostDto result = postService.save(generateCreatePost(), userId, categoryId);

    }

    private SavePostRequest generateCreatePost(){
        SavePostRequest createPost = new SavePostRequest();
        createPost.setTitle("Test Post");
        createPost.setDescription("This is a Test.");

        return createPost;
    }



}