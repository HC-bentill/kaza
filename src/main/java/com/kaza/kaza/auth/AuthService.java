package com.kaza.kaza.auth;

import com.kaza.kaza.dto.AuthResponse;
import com.kaza.kaza.dto.LoginRequest;
import com.kaza.kaza.dto.RegisterRequest;
import com.kaza.kaza.user.User;
import com.kaza.kaza.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(
                    false,
                    "Email does not exist"
            );
        }

        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        // Return response
        return new AuthResponse(
                true,
                "User Registered Successfully"
        );
    }

    public AuthResponse login(LoginRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(
                    false,
                    "Invalid Credentials"
            );
        }

        // Return response
        return new AuthResponse(
                true,
                "Login successful"
        );
    }
}