package edu.lethe.gameslib.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Profile("prod")
@Component
public class JwtUtil {

    @Value("${jwt.keystring}")
    private String signatureKey;
    private SecretKey key;

    @Value("${jwt.token-timeout}")
    private Long tokenTimeoutInMilliseconds;

    @Value("${jwt.refresh-timeout}")
    private Long refreshTokenTimeoutInMilliseconds;

    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(signatureKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, String role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenTimeoutInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTimeoutInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
