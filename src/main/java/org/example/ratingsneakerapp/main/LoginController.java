package org.example.ratingsneakerapp.main;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Label;
import org.example.ratingsneakerapp.JwtCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtCore jwtCore;
    @Autowired
    private UserRepository userRepository;



    @PostMapping("/log")
    public ResponseEntity<?> login(@RequestBody SignInRequest sign) {
        Authentication auth = null;
        if (userRepository.findByUsername(sign.getUsername()) != null) {
                auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sign.getUsername(), sign.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(auth);
                String jwt = jwtCore.generateToken(auth);
                return ResponseEntity.ok(jwt);
            }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
