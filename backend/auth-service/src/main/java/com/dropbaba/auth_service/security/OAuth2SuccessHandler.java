package com.dropbaba.auth_service.security;

import com.dropbaba.auth_service.entity.Role;
import com.dropbaba.auth_service.entity.User;
import com.dropbaba.auth_service.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Extract basic info
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // Create temporary User object to pass to JwtUtil (no DB access here)
        User user = User.builder()
                .email(email)
                .name(name)
                .role(Role.USER)
                .build();

        // Generate JWT
        String token = jwtUtil.generateToken(user);

        // Redirect with token to frontend (you'll capture it later)
        response.sendRedirect("http://localhost:3000/oauth2/success?token=" + token);
    }
}
