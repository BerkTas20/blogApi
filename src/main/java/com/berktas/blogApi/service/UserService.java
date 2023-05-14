package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.model.dto.PhotoDto;
import com.berktas.blogApi.model.dto.UserDto;
import com.berktas.blogApi.model.entity.Post;
import com.berktas.blogApi.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto save(SaveAndUpdateUserRequest saveUserRequest);

    UserDto update(Long id, SaveAndUpdateUserRequest saveUserRequest);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();

    void uploadProfilePhoto(Long userId,MultipartFile file);

    void uploadCoverPhoto(Long userId, MultipartFile file);

    void deleteProfilePhoto(User user);

    void deleteCoverPhoto(User user);

    byte[] getProfilePhoto(Long userId);


}
