package com.berktas.blogApi.utils;



import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.model.entity.User;
import com.berktas.blogApi.model.enums.Role;
import com.berktas.blogApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestUserUtility {

    @Autowired
    UserRepository adminRepository;

    public User getOrCreateTestAdmin() {
        return adminRepository.findByUsername("admin").orElseGet(() -> {
            User admin = User.create(SaveAndUpdateUserRequest.builder()
                    .username("admin")
                    .firstName("Test Admin")
                    .password("12345")
                    .role(Role.ROLE_ADMIN)
                    .build());
            return adminRepository.save(admin);
        });
    }

}
