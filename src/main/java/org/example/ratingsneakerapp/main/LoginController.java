package org.example.ratingsneakerapp.main;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ratingsneakerapp.JwtCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtCore jwtCore;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/log")
    public String main(Model model, HttpServletRequest request) {
        model.addAttribute("newUser", new User());
        return jwtService.GetRoleByJwt(request, model, "login/login");
    }

    @PostMapping("/log")
    public String login(@ModelAttribute("newUser") User user, Model model, HttpServletResponse response, HttpServletRequest request) {
        Authentication auth = null;
        if (userRepository.findByUsername(user.getUsername()) != null) {
                try {
                    auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                } catch (BadCredentialsException e) {
                    model.addAttribute("error", 2);
                    return jwtService.GetRoleByJwt(request, model, "login/login");
                }
                SecurityContextHolder.getContext().setAuthentication(auth);

                String jwt = jwtCore.generateToken(auth);
                response.addHeader("Authorization", "Bearer " + jwt);
                return jwtService.GetRoleByJwt(request, model, "login/sucsess");
            }
            model.addAttribute("error", 2);
            return jwtService.GetRoleByJwt(request, model, "login/login");

    }
}
