package com.example.microserivcehotel.user_service.Config;

import com.example.microserivcehotel.user_service.service.UserDetailServiceImpl;
import com.example.microserivcehotel.user_service.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtSecurityConfig jwtUtils;

    @Autowired
    private  UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        if (request.getRequestURI().startsWith("/api/auth/")) {
//            filterChain.doFilter(request, response);
//            return;
//        }


        String token = getTokenFromHeader(request); // Use header instead of body // Extract token from body

        if (token != null && jwtUtils.validateToken(token)) {
            String email = jwtUtils.extractEmail(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
