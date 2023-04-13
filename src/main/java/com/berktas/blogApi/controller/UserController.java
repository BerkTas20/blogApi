package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.PostResponse;
import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.controller.requests.SavePostRequest;
import com.berktas.blogApi.controller.requests.UpdatePostRequest;
import com.berktas.blogApi.model.dto.PostDto;
import com.berktas.blogApi.model.dto.UserDto;
import com.berktas.blogApi.service.PostService;
import com.berktas.blogApi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }
}
