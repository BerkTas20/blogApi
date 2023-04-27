package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.entity.Category;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.Tag;
import com.berktas.blogApi.model.entity.User;
import com.berktas.blogApi.model.mapper.PostMapper;
import com.berktas.blogApi.repository.CategoryRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.TagRepository;
import com.berktas.blogApi.repository.UserRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
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
    private final TagRepository tagRepository;

    @Override
    public PostDto save(SavePostRequest savePostRequest, Long userId, Long categoryId, MultipartFile photo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("User not found" + categoryId));
        Post post = new Post();
        post.setTitle(savePostRequest.getTitle());
        post.setDescription(savePostRequest.getDescription());
        post.setUser(user);
        post.setCategory(category);

        // Fotoğrafı byte dizisine dönüştür
        try {
            byte[] photoBytes = photo.getBytes();
            post.setPhoto(photoBytes);
        } catch (IOException e) {
            logger.error("Fotoğraf yüklenirken bir hata oluştu: " + e.getMessage());
            throw new RuntimeException("Fotoğraf yüklenirken bir hata oluştu. Lütfen tekrar deneyin.");
        }

        return postMapper.entityToDto(postRepository.save(post));

    }

    @Override
    public PostDto update(Long id, UpdatePostRequest updatePostRequest, MultipartFile photo) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        post.setTitle(updatePostRequest.getTitle());
        post.setDescription(updatePostRequest.getDescription());
        post.setCategory(category);

        if (photo != null && !photo.isEmpty()){
            try {
                byte[] photoBytes = photo.getBytes();
                post.setPhoto(photoBytes);
            } catch (IOException e) {
                logger.error("Fotoğraf yüklenirken bir hata oluştu: " + e.getMessage());
                throw new RuntimeException("Fotoğraf yüklenirken bir hata oluştu. Lütfen tekrar deneyin.");
            }
        }
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

}
