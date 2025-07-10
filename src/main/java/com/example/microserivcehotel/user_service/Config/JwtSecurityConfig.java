package com.example.microserivcehotel.user_service.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtSecurityConfig {

    private  final String SECRET_KEY = "ValidtorHashed";
    private final long EXPIRATION = 1000000;

    public String jwtToken(String email) {
        return Jwts.builder().setSubject(email).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION )).
                signWith(SignatureAlgorithm.HS384,SECRET_KEY).
                compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder().
                setSigningKey(SECRET_KEY).
                build().
                parseClaimsJws(token).
                getBody().
                getSubject();
    }

    public boolean validateToken(String token){
       try { Jwts.parserBuilder().
               setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
           return true;
       } catch (JwtException | IllegalArgumentException e) {

           return false;
       }
    }
}
