package com.berktas.blogApi.service;

import com.berktas.blogApi.dto.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    PhotoDto savePhotoToPost(Long postId, Long userId, MultipartFile photo) throws IOException;

}
