package com.example.restaurant.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.restaurant.dto.ApiResponse;
import  com.example.restaurant.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            claims.get("email", String.class),
                            null,
                            List.of(new SimpleGrantedAuthority(roles))
                    );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}