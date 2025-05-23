package com.snap.pay.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {
    private Long id;
    private String username;
    private String mobileNumber;
    private String email;
    private String error;
    private Long expiresIn;
}