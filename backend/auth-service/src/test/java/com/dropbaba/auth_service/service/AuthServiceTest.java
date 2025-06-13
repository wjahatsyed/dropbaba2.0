package com.dropbaba.auth_service.service;

import com.dropbaba.auth_service.dto.AuthRequest;
import com.dropbaba.auth_service.dto.AuthResponse;
import com.dropbaba.auth_service.dto.LoginRequest;
import com.dropbaba.auth_service.entity.Role;
import com.dropbaba.auth_service.entity.User;
import com.dropbaba.auth_service.repository.UserRepository;
import com.dropbaba.auth_service.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        // Arrange
        AuthRequest request = AuthRequest.builder()
                .name("Wajahat")
                .email("wajahat@example.com")
                .phone("03331234567")
                .password("password")
                .role(Role.USER)
                .build();

        String hashedPassword = "hashed-password";
        User savedUser = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(hashedPassword)
                .role(request.getRole())
                .enabled(true)
                .build();

        when(passwordEncoder.encode(request.getPassword())).thenReturn(hashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtUtil.generateToken(any(User.class))).thenReturn("fake-jwt-token");

        // Act
        AuthResponse response = authService.register(request);

        // Assert
        assertNotNull(response);
        assertEquals("fake-jwt-token", response.getAccessToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldLoginSuccessfullyWithEmail() {
        LoginRequest request = LoginRequest.builder()
                .emailOrPhone("wajahat@example.com")
                .password("password")
                .build();

        User user = User.builder()
                .id(UUID.randomUUID())
                .name("Wajahat")
                .email("wajahat@example.com")
                .phone("03331234567")
                .password("hashed-password")
                .role(Role.USER)
                .enabled(true)
                .build();

        when(userRepository.findByEmailOrPhone(request.getEmailOrPhone(), request.getEmailOrPhone()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(any(User.class))).thenReturn("test-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("test-token", response.getAccessToken());
        verify(userRepository, times(1)).findByEmailOrPhone(any(), any());
    }

}
