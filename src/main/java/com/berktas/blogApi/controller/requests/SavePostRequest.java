package com.berktas.blogApi.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavePostRequest {

    @NotBlank
    private String description;

    @NotBlank
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;
}
