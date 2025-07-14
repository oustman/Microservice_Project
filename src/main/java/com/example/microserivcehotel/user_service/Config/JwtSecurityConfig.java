package com.example.microserivcehotel.user_service.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
@Service
public class JwtSecurityConfig {

    // Generate a fixed 384-bit key (run once and save the value)
    @Value("${app.jwt.secret}")
    private String secretKeyBase64;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyBase64));
    }




    public String jwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(getSecretKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder().
                setSigningKey(getSecretKey()).
                build().
                parseClaimsJws(token).
                getBody().
                getSubject();
    }

    public boolean validateToken(String token){
       try { Jwts.parserBuilder().
               setSigningKey(getSecretKey()).build().parseClaimsJws(token);
           return true;
       } catch (JwtException | IllegalArgumentException e) {
           return false;
       }
    }

//    void generateKey(){
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS384);
//        System.out.println("Base64 Secret Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
//    }

}
