package com.snap.pay.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}