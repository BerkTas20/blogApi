package com.berktas.blogApi.service;

import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(SaveAndUpdateUserRequest saveUserRequest);

    UserDto update(Long id, SaveAndUpdateUserRequest saveUserRequest);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();
}
