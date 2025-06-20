package com.dropbaba.auth_service.service;

import com.dropbaba.auth_service.dto.AuthRequest;
import com.dropbaba.auth_service.dto.AuthResponse;
import com.dropbaba.auth_service.dto.LoginRequest;
import com.dropbaba.auth_service.entity.User;
import com.dropbaba.auth_service.repository.UserRepository;
import com.dropbaba.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public AuthResponse login(LoginRequest request) {
        // Find by email or phone
        User user = userRepository.findByEmailOrPhone(request.getEmailOrPhone(), request.getEmailOrPhone())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email or phone"));

        // Match password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .build();
    }

}
