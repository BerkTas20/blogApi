package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.core.security.SpringContext;
import com.berktas.blogApi.dto.UserDto;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.mapper.PhotoMapper;
import com.berktas.blogApi.mapper.UserMapper;
import com.berktas.blogApi.repository.PhotoRepository;
import com.berktas.blogApi.repository.UserRepository;
import com.berktas.blogApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    private final UserMapper userMapper;

    private final PhotoMapper photoMapper;
    private final SpringContext springContext;


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

    @Override
    public void uploadProfilePhoto(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        try {
            user.updateProfilePhoto(file);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadCoverPhoto(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
        try {
            user.updateCoverPhoto(file);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProfilePhoto(User user) {
        user.deleteProfilePhoto();
        userRepository.save(user);
    }

    @Override
    public void deleteCoverPhoto(User user) {
        user.deleteCoverPhoto();
        userRepository.save(user);
    }

    @Override
    public byte[] getProfilePhoto(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Optional<byte[]> profilePhotoOptional = Optional.ofNullable(user.getProfilePhoto());
        if (profilePhotoOptional.isEmpty()){
            throw new EntityNotFoundException("User does not have a profile photo");
        }

        return profilePhotoOptional.get();
    }
}
