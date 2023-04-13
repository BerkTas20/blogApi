package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.model.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveAndUpdateUserRequest {

        private String firstName;
        private String lastName;
        private String username;
        private String phone;
        private String email;
        private String password;
        private String role;


}
