package com.example.microserivcehotel.user_service.mapper;

import com.example.microserivcehotel.user_service.DTO.LoginDTO;
import com.example.microserivcehotel.user_service.DTO.SignUpDTO;
import com.example.microserivcehotel.user_service.DTO.UserResponseDTO;
import com.example.microserivcehotel.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapperInterface {
    UserMapperInterface INSTANCE = Mappers.getMapper(UserMapperInterface.class);

    // Map SignUpDto -> User entity
    @Mapping(target = "id", ignore = true) // Auto-generated, skip mapping
    @Mapping(target = "password", source = "password") // Match field names
    User dtoToEntity(SignUpDTO signUpDTO);

    // Map LoginDTO -> User entity
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User dtoToLoginEntity(LoginDTO loginDTO);


    //Map UserResponse DTO to
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    UserResponseDTO entityToResponse(User user);


}
