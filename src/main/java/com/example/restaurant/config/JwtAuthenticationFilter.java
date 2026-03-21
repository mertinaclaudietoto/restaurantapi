package com.example.restaurant.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.restaurant.dto.ApiResponse;
import com.example.restaurant.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import  jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.info("JWT FILTER EXECUTED");

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            logger.info("Token: " + token);

            if (!jwtService.isValid(token)) {
                logger.info("JWT invalide");

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // Créer l'objet ApiResponse
                ApiResponse<Object> apiResponse = new ApiResponse<>(
                    401,
                    "JWT invalide",
                    null
                );

                // Convertir en JSON et écrire dans la réponse
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(apiResponse));
                return;
            }
            // Authentification Spring Security
            // String email = jwtService.extractEmail(token);
            Claims claims = jwtService.extractAllClaims(token);
            String roles = claims.get("role", Long.class) == 1L ? "ROLE_ADMIN" : "ROLE_USER";
            System.out.println(roles);
            logger.info("User role"+roles);

            UserDetails user = User.builder()
            .username(claims.get("email", String.class))
            .password("") // pas besoin du mot de passe ici
            .authorities(roles) // on peut passer directement "ROLE_USER" ou "ROLE_ADMIN"
            .build();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
                    }

        filterChain.doFilter(request, response);
    }
}