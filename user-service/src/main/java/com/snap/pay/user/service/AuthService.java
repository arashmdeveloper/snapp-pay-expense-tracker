package com.snap.pay.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snap.pay.user.dto.AuthRequest;
import com.snap.pay.user.dto.AuthResponse;
import com.snap.pay.user.dto.JwtToken;
import com.snap.pay.user.dto.RegisterRequest;
import com.snap.pay.user.dto.RegisterResponse;
import com.snap.pay.user.dto.UserDto;
import com.snap.pay.user.model.User;
import com.snap.pay.user.repository.UserRepository;

import jakarta.security.auth.message.AuthException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) throws AuthException {
    	
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AuthException("Username already in use");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("Email already in use");
        }

        User user = User.builder()
                .username(request.getUsername())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
        		.id(user.getId())
                .username(user.getUsername())
                .mobileNumber(user.getMobileNumber())
                .email(user.getEmail())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) throws AuthException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("User not found"));

        JwtToken token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token.getToken())
                .user(mapToUserDto(user))
                .expiresIn(token.getExpireIn())
                .build();
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}