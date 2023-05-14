package com.berktas.blogApi.core.utils.db;


import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.enums.Role;
import com.berktas.blogApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DbLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long adminCount = userRepository.countByRole(Role.ROLE_ADMIN);
        if (adminCount == 0) {
            User admin = User.create(SaveAndUpdateUserRequest.builder()
                    .firstName("Admin")
                    .username("admin")
                    .password("12345")
                    .role(Role.ROLE_ADMIN)
                    .build());
            userRepository.save(admin);
        }
    }
}