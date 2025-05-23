package com.snap.pay.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.snap.pay.user.dto.JwtToken;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    public JwtToken generateToken(UserDetails userDetails) {
    	Long issueDate = System.currentTimeMillis();
    	Long expiration = System.currentTimeMillis() + expirationMs ;
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(issueDate))
                .setExpiration(new Date(expiration))
                .claim("roles", userDetails.getAuthorities())
                .signWith(getSigningKey())
                .compact();
        return JwtToken.builder()
        		.token(token)
        		.subject(userDetails.getUsername())
        		.issueAt(new Date(issueDate))
        		.expireIn(new Date(expiration))
        		.build();
        
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // For Base64 keys
        // OR
        // byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8); // For raw strings
        
        // Explicitly specify HS256 (256-bit key)
        return Keys.hmacShaKeyFor(keyBytes); // JJWT will handle algorithm internally
    }
}