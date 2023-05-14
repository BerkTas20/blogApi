package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.dto.UserDto;
import com.berktas.blogApi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(SaveAndUpdateUserRequest saveAndUpdateUserRequest) {
        return ResponseEntity.ok(userService.save(saveAndUpdateUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> save(@PathVariable Long id, SaveAndUpdateUserRequest saveAndUpdateUserRequest) {
        return ResponseEntity.ok(userService.update(id, saveAndUpdateUserRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/{userId}/profilePhoto")
    public void uploadProfilePhoto(@PathVariable Long userId, @RequestParam(name = "file") MultipartFile file) {
        userService.uploadProfilePhoto(userId, file);
    }

    @PatchMapping("/{userId}/coverPhoto")
    public void uploadCoverPhoto(@PathVariable Long userId, @RequestParam(name = "file") MultipartFile file) {
        userService.uploadCoverPhoto(userId, file);
    }

    @GetMapping("/{userId}/profilePhoto")
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable Long userId) {
        byte[] profilePhoto = userService.getProfilePhoto(userId);
        if (profilePhoto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePhoto);
    }
}

