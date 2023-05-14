package com.berktas.blogApi.service.impl;

import com.berktas.blogApi.dto.PhotoDto;
import com.berktas.blogApi.model.Photo;
import com.berktas.blogApi.model.Post;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.mapper.PhotoMapper;
import com.berktas.blogApi.repository.PhotoRepository;
import com.berktas.blogApi.repository.PostRepository;
import com.berktas.blogApi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Mock
    private PhotoMapper photoMapper;

    @Captor
    private ArgumentCaptor<Photo> photoCaptor;
    @Test
    public void shouldSavePhotoToPostSuccessfully() throws IOException {
        Long postId = 1L;
        Long userId = 2L;
        Post post = new Post();
        post.setId(postId);
        User user = new User();
        user.setId(userId);

        byte[] photoBytes = "Test Photo".getBytes();
        MultipartFile photo = new MockMultipartFile("photo", photoBytes);

        Photo savedPhoto = new Photo();
        savedPhoto.setId(1L);
        savedPhoto.setData(photoBytes);
        savedPhoto.setPost(post);
        savedPhoto.setUser(user);

        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Mockito.when(photoRepository.save(Mockito.any(Photo.class))).thenReturn(savedPhoto);

        PhotoDto result = photoService.savePhotoToPost(postId, userId, photo);

        Mockito.verify(postRepository).findById(postId);
        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(photoRepository).save(photoCaptor.capture());
        Photo capturedPhoto = photoCaptor.getValue();
        Assert.assertArrayEquals(photoBytes, capturedPhoto.getData());
    }




}