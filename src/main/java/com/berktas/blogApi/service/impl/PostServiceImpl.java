package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.dto.PostDto;
import com.berktas.blogApi.model.Category;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.Score;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.model.entity.*;
import com.berktas.blogApi.mapper.PostMapper;
import com.berktas.blogApi.repository.*;
import com.berktas.blogApi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final ScoreRepository rateRepository;

    @Override
    public PostDto save(SavePostRequest savePostRequest, Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("User not found" + categoryId));
        Post post = new Post();
        post.setTitle(savePostRequest.getTitle());
        post.setDescription(savePostRequest.getDescription());
        post.setUser(user);
        post.setCategory(category);


        return postMapper.entityToDto(postRepository.save(post));

    }

    @Override
    public PostDto update(Long id, UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        post.setTitle(updatePostRequest.getTitle());
        post.setDescription(updatePostRequest.getDescription());
        post.setCategory(category);

        return postMapper.entityToDto(postRepository.save(post));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepository.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map(postMapper::entityToDto)
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getOnePostById(Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found" + postId));
        return postMapper.entityToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("User not found" + categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);
        return postMapper.entityListToDtoList(posts);
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        List<Post> posts = postRepository.findByUser(user);
        return postMapper.entityListToDtoList(posts);
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepository.searchByTitle("%" + keyword + "%");
        return postMapper.entityListToDtoList(posts);
    }


    public List<Post> getLatestPostsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return postRepository.findTop5ByUserOrderByCreatedDateTimeDesc(user);
    }

    public void incrementViews(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Integer views = post.getViews();

        if (views == null) {
            views = 0;
        }
        postRepository.save(post);
    }

    @Override
    public void ratePost(Long postId, Long userId, double rating) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Score rate = new Score();
        rate.setPost(post);
        rate.setUser(user);
        rate.setRating(rating);

        rateRepository.save(rate);
        updatePostAverageRating(post);
    }


    private void updatePostAverageRating(Post post) {
        List<Score> ratings = rateRepository.findByPost(post);
        double totalRating = 0.0;

        for (Score rating : ratings) {
            totalRating += rating.getRating();
        }

        double averageRating = totalRating / ratings.size();
        post.setScore(averageRating);

        postRepository.save(post);
    }
}
