package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.SaveAndUpdateCommentRequest;
import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.model.dto.CommentDto;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.dto.UserDto;
import com.berktas.blogApi.model.entity.Comment;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.User;
import com.berktas.blogApi.model.mapper.CommentMapper;
import com.berktas.blogApi.model.mapper.UserMapper;
import com.berktas.blogApi.repository.CommentRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.UserRepository;
import com.berktas.blogApi.service.CommentService;
import com.berktas.blogApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto save(SaveAndUpdateUserRequest saveUserRequest) {
        User user = User.create(saveUserRequest);
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id, SaveAndUpdateUserRequest saveUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        user.setUsername(saveUserRequest.getUsername());
        user.setLastName(saveUserRequest.getLastName());
        user.setPassword(saveUserRequest.getPassword());
        user.setEmail(saveUserRequest.getEmail());
        user.setPhone(saveUserRequest.getPhone());
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found" + id));
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.entityListToDtoList(userRepository.findAll());
    }


}
