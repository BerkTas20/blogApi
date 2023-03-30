package com.berktas.blogApi.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;

    @Override
    public PostDto save(SavePostRequest savePostRequest, Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("User not found" + categoryId)).getCategory();
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
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id)).getCategory();
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
        ;
        return postMapper.entityToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("User not found" + categoryId)).getCategory();
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

    @Override
    public PostResponse getPostsByTag(Long tagId, int page, int size) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found" + tagId));
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
        Page<Post> posts = postRepository.findByTagsIn(Collections.singletonList(tag), pageable);
        List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

        List<PostDto> postDtos = content.stream().map(postMapper::entityToDto).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());

        return postResponse;

    }
}
