package com.example.demo.service;

// JwtService.java

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRET = "your-256-bit-secret-key-should-be-at-least-32-bytes-long";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, List<GrantedAuthority> roles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 15); // 15 minutes

        List<String> roleNames = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        redisTemplate.opsForValue().set(
                "user-last-valid-token:" + username,now.getTime()+1000); // To address Redis saving latency

        var x =  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("roles", roleNames)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        return  x;
    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return extractAllClaims(token).get("roles", List.class);
    }

    public Date extractIssuedAt(String token) {
        return extractAllClaims(token).getIssuedAt();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean isTokenStillValid(String username, Date issuedAt) {
        Object raw = redisTemplate.opsForValue().get("user-last-valid-token:" + username);

        if (raw instanceof List<?> list && list.size() == 2 && list.get(1) instanceof Number ts) {
            Long lastValidTime = ts.longValue();
            return issuedAt.getTime() >= lastValidTime;
        } else if (raw instanceof Long lastValidTime) {
            return issuedAt.getTime() >= lastValidTime;
        }
        return true;

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void updateLastValidTime(String username) {
        redisTemplate.opsForValue().set(
                "user-last-valid-token:" + username,
                System.currentTimeMillis(),
                1, TimeUnit.DAYS // Optional TTL
        );
    }
}
