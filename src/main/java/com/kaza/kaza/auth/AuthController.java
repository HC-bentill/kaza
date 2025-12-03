package com.kaza.kaza.auth;

import com.kaza.kaza.config.ApiResponse;
import com.kaza.kaza.dto.AuthResponse;
import com.kaza.kaza.dto.LoginRequest;
import com.kaza.kaza.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);

        if(!response.getSuccess()){
            return ResponseEntity.ok(new ApiResponse<>(false, response.getMessage(), null ));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, response.getMessage(), null ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>>login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
//        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        if(!response.getSuccess()){
            return ResponseEntity.ok(new ApiResponse<>(false, response.getMessage(), null ));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, response.getMessage(), null ));
    }
}