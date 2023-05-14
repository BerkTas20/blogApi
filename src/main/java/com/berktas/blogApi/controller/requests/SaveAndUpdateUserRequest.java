package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@Setter
public class SaveAndUpdateUserRequest {

        private String firstName;
        private String lastName;
        private String username;
        private String phone;
        private String email;
        private String password;
        private Role role;

}
