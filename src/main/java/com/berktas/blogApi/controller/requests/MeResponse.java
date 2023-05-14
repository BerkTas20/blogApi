package com.berktas.blogApi.controller.requests;


import com.berktas.blogApi.enums.Role;
import com.berktas.blogApi.enums.UserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeResponse {

    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private UserType userType;


}
