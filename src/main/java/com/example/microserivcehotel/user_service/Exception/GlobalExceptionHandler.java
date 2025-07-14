package com.example.microserivcehotel.user_service.Exception;


import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthException(AuthException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> error = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((errors) -> {
         if(errors instanceof  FieldError){
             String field = ((FieldError) errors).getField(); //Will get Mail & Password
             String defaultMessage = errors.getDefaultMessage();//Will get Mesg Like Email not vaild what we have done on DTO class
             error.put(field,defaultMessage);
         }} );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
