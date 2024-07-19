package org.example.ratingsneakerapp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.example.ratingsneakerapp.main.Role;
import org.example.ratingsneakerapp.main.UserDetailImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCore {
    @Value("${spring.app.secret}")
    private String secret;


    @Value("${spring.app.lifetime}")
    private int lifetime;


    private UserDetailImpl userDetail;

    public String generateToken(Authentication authentication) {
       userDetail = (UserDetailImpl) authentication.getPrincipal();
        String roleString = userDetail.getRole().toString();
       return Jwts.builder()
               .setSubject(roleString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
     }


}