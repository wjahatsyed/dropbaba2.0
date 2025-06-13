package com.dropbaba.auth_service.service;

import com.dropbaba.auth_service.dto.AuthRequest;
import com.dropbaba.auth_service.dto.AuthResponse;
import com.dropbaba.auth_service.dto.LoginRequest;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(LoginRequest request);

}
