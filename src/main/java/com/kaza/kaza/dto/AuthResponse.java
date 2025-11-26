package com.kaza.kaza.dto;

import com.kaza.kaza.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String email;
    private String fullName;
    private Role role;
    private String message;
}