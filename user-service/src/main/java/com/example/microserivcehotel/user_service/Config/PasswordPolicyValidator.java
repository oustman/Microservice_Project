package com.example.microserivcehotel.user_service.Config;

public class PasswordPolicyValidator {
    public static boolean isValid(String password){
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*")&&
                password.matches(".*[a-z].*")&&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()].*");
    }
}
