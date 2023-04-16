package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.model.enums.Role;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String token;

}