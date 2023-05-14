package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.core.exception.EntityNotFoundException;
import com.berktas.blogApi.dto.PhotoDto;
import com.berktas.blogApi.model.Photo;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.mapper.PhotoMapper;
import com.berktas.blogApi.repository.PhotoRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.UserRepository;
import com.berktas.blogApi.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public PhotoDto savePhotoToPost(Long postId, Long userId, MultipartFile photo) throws IOException {
        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found" + postId));
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found" + userId));
            byte[] photoBytes = photo.getBytes();
            Photo newPhoto = new Photo();
            newPhoto.setData(photoBytes);
            newPhoto.setPost(post);
            newPhoto.setUser(user);
            return photoMapper.entityToDto(photoRepository.save(newPhoto));
        } catch (IOException e) {

            throw new IOException("Error while processing photo", e);
        }
    }
}
