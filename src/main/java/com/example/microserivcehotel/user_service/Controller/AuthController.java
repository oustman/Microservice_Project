package com.example.microserivcehotel.user_service.Controller;


import com.example.microserivcehotel.user_service.DTO.LoginDTO;
import com.example.microserivcehotel.user_service.DTO.SignUpDTO;
import com.example.microserivcehotel.user_service.DTO.UserResponseDTO;
import com.example.microserivcehotel.user_service.service.UserService;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    private ResponseEntity<UserResponseDTO> registerUser(@Valid  @RequestBody SignUpDTO signUpDTO)
    {
        UserResponseDTO userResponseDTO = userService.regsiterDTO(signUpDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
    @PostMapping("/login")
    private ResponseEntity<String>  loggingInUser(@Valid @RequestBody LoginDTO loginDTO) throws AuthException {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok()  .header("Authorization", "Bearer " + token).build();

    }


}
