package com.dropbaba.auth_service.service;

import com.dropbaba.auth_service.dto.AuthRequest;
import com.dropbaba.auth_service.dto.AuthResponse;
import com.dropbaba.auth_service.entity.User;
import com.dropbaba.auth_service.repository.UserRepository;
import com.dropbaba.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(AuthRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .build();
    }
}
