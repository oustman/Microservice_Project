package com.example.microserivcehotel.user_service.Controller;


import com.example.microserivcehotel.user_service.DTO.UserResponseDTO;
import com.example.microserivcehotel.user_service.service.UserService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")    private ResponseEntity<UserResponseDTO> getProfile(@RequestHeader("Authorization") String token)
            throws AuthException {
        String email = userService.extractEmailFromToken(token);
        UserResponseDTO byEmail = userService.findByEmail(email);
        return ResponseEntity.ok(byEmail);
    }

}
