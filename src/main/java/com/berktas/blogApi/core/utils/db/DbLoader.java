package com.berktas.blogApi.core.utils.db;


import com.berktas.blogApi.core.entity.Admin;
import com.berktas.blogApi.model.enums.Role;
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
        Long adminCount = userRepository.countByRole(Role.ROLE_ADMIN.toString());
        if (adminCount == 0) {
            Admin admin = Admin.create("Admin", "-", "admin", encoder.encode("12345"));
            userRepository.save(admin);
        }
    }
}
