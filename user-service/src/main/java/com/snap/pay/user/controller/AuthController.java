package com.snap.pay.user.controller;

import com.snap.pay.user.dto.AuthRequest;
import com.snap.pay.user.dto.AuthResponse;
import com.snap.pay.user.dto.RegisterRequest;
import com.snap.pay.user.dto.RegisterResponse;
import com.snap.pay.user.service.AuthService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest request) throws AuthException {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) throws AuthException {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}