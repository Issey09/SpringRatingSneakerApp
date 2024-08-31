package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;



    @PostMapping("/reg")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest sign) {

        if (userRepository.findByUsername(sign.getUsername()) == null) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(sign.getPassword()));
        user.setUsername(sign.getUsername());
        user.setEmail(sign.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok("Registration Successful");
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }
}
