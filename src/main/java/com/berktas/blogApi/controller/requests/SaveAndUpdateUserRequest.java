package com.berktas.blogApi.controller.requests;

import com.berktas.blogApi.enums.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAndUpdateUserRequest {

        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @NotBlank(message = "Last name cannot be blank")
        private String lastName;
        private String username;

        @Pattern(regexp = "\\d{10}", message = "Phone must be a 10-digit number")
        private String phone;

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        private String email;
        private String password;
        private Role role;

}
