package com.sangsil.ssrframework.global.config;

import com.sangsil.ssrframework.domain.user.dto.UserDto;
import com.sangsil.ssrframework.domain.user.repository.UserRepository;
import com.sangsil.ssrframework.global.code.RoleCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
        LocalDateTime now = LocalDateTime.now();
        return args -> {
            // 이미 데이터가 있으면 스킵
            if (userRepository.count() == 0) {
                UserDto.CreateRequest createRequest = new UserDto.CreateRequest();
                createRequest.setUsername("admin");
                createRequest.setPassword(passwordEncoder.encode("1234"));
                createRequest.setName("관리자");
                createRequest.setEmail("admin@member.com");
                createRequest.setRole(RoleCode.ROLE_ADMIN.name());

                userRepository.saveAll(List.of(createRequest.toEntity()));
                System.out.println("✅ 초기 유저 데이터 등록 완료");
            }
        };
    }
}
