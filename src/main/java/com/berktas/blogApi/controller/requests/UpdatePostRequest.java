package com.berktas.blogApi.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    private String description;
    private String title;
    private Long userId;

}
