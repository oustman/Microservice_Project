package com.example.microserivcehotel.user_service.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {
        // Generate a key for HS384 (384 bits)
    String key =  "a-valid-string-secret-that-is-at-least-384-bits-long";
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);


        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Encoded Key: " + encodedKey);
        // Print Base64-encoded key (store this securely)
        //System.out.println("Base64 Key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}


