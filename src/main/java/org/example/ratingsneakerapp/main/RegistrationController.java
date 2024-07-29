package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @GetMapping("/reg")
    public String main(Model model, HttpServletRequest request) {
        model.addAttribute("newUser", new User());

        // Проверяем, аутентифицирован ли пользователь

        return jwtService.GetRoleByJwt(request, model, "registration/reg");
    }

    @PostMapping("/reg")
    public String registerUser(@ModelAttribute("newUser") User user, Model model, HttpServletRequest request) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            // Проверяем, аутентифицирован ли пользователь
            return jwtService.GetRoleByJwt(request, model, "registration/sucsess");
        }else {
            model.addAttribute("NameTaken", "");
            return jwtService.GetRoleByJwt(request, model, "registration/reg");
        }

    }
}
