package com.bananaapps.MyOnlineShoppingService.domain.filters;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Role;
import com.bananaapps.MyOnlineShoppingService.domain.entities.User;
import com.bananaapps.MyOnlineShoppingService.domain.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        System.out.println("Hola ----- --- Entro y token = " + token);
        if (token != null && jwtUtils.validateToken(token)) {
            System.out.println("Hola ----- --- Token valido");
            UserDetails userDetails = getUserDetails(token);
            String username = userDetails.getUsername();
            System.out.println("Hola ----- --- Username: " + username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }

    private UserDetails getUserDetails(String token) {
        return User.builder()
                .username(jwtUtils.getUsernameFromToken(token))
                .role(jwtUtils.getRole(token))
                .build();
    }
}