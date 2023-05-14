package com.berktas.blogApi.controller;

import com.berktas.blogApi.dto.PhotoDto;
import com.berktas.blogApi.service.PhotoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
@Tag(name = "Photo")
public class PhotoController {
    private final PhotoService photoService;
    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<PhotoDto> createPostPhoto(@PathVariable Long postId, @PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(photoService.savePhotoToPost(postId, userId, file));
    }
}
