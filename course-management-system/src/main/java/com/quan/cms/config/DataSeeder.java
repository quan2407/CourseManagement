package com.quan.cms.config;

import com.quan.cms.entity.User;
import com.quan.cms.enums.Role;
import com.quan.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.existsByUsername("admin")) {
            return;
        }

        User admin = User.builder()
                .username("admin")
                .passwordHash(passwordEncoder.encode("123456"))
                .email("admin@gmail.com")
                .fullName("System Admin")
                .role(Role.ADMIN)
                .isActive(true)
                .build();

        userRepository.save(admin);

        System.out.println("Admin account created");
    }
}