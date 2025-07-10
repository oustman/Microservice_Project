package com.example.microserivcehotel.user_service.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    @NotBlank(message = "Email is required")
    @Email
     String email;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Password is required")
    @Size(min =  6, message = "The Password Must Be Around 6 characters")
    private String password;
}
