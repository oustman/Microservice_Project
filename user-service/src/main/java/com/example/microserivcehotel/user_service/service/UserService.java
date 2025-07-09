package com.example.microserivcehotel.user_service.service;


import com.example.microserivcehotel.user_service.Config.JwtSecurityConfig;
import com.example.microserivcehotel.user_service.Config.PasswordPolicyValidator;
import com.example.microserivcehotel.user_service.DTO.LoginDTO;
import com.example.microserivcehotel.user_service.DTO.SignUpDTO;
import com.example.microserivcehotel.user_service.DTO.UserResponseDTO;
import com.example.microserivcehotel.user_service.Exception.InvalidPassWordException;
import com.example.microserivcehotel.user_service.UserRepo.UserRepo;
import com.example.microserivcehotel.user_service.mapper.UserMapperInterface;
import com.example.microserivcehotel.user_service.model.User;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtSecurityConfig jwtUtils;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserResponseDTO regsiterDTO(SignUpDTO signUpDTO){
        log.info("Regisetring The User for " + signUpDTO.getName());
        if (! PasswordPolicyValidator.isValid(signUpDTO.getPassword())){
            throw new InvalidPassWordException("Password Is Not Valid Check The character & Length");
        }
        User user = UserMapperInterface.INSTANCE.dtoToEntity(signUpDTO);
        user.setPassWord(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        User savedUser = userRepo.save(user);
        return UserMapperInterface.INSTANCE.entityToResponse(savedUser);
    }

    public String login(LoginDTO loginDTO) throws AuthException {
         User user =   userRepo.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new AuthException("Email Doesn't Exist"))
        if (!bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassWord())) {
            throw new AuthException("Invalid credentials");
        }

        return jwtUtils.jwtToken(user.getEmail());

    }
}
