package com.example.prescription.reminder.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "yourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKey"; // Replace
    // with
    // a
    // secure
    // key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // Generate a signing key
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // Convert the secret key to bytes
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName()); // Generate a Key object
    }

    // Generate a JWT token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Create the token
    private String createToken(Map<String, Object> claims, String subject) {
        return "Bearer "+Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Use Key object for signing
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract all claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()) // Use Key object for validation
                .build().parseClaimsJws(token).getBody();
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String tokenUsername = extractUsername(jwtToken);
        return userDetails.getUsername().equals(tokenUsername) && !isTokenExpired(jwtToken);
    }
}
