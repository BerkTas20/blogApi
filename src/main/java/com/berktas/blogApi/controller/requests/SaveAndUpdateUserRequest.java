package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.enums.Role;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAndUpdateUserRequest {

        private String firstName;
        private String lastName;
        private String username;
        private String phone;
        private String email;
        private String password;
        private Role role;

}
