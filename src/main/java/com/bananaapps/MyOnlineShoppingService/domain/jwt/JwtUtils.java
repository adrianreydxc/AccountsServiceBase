package com.bananaapps.MyOnlineShoppingService.domain.jwt;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Role;
import com.bananaapps.MyOnlineShoppingService.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try{

            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;

        }catch (Exception e){

            return false;
        }
    }
        public Claims getClaims(String token) {
        Claims claims = parseClaims(token);
        return claims;
    }

        public Role getRole(String token){
        String role= getClaims(token).get("role",String.class);
        return Role.valueOf(role);
    }

        private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}