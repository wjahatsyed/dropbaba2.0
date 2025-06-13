package com.dropbaba.auth_service.dto;

import com.dropbaba.auth_service.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    private String email;
    private String phone;
    private String name;
    private String password;
    private Role role;
}