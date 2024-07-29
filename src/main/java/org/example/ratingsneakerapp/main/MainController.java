package org.example.ratingsneakerapp.main;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ratingsneakerapp.admin.Sneaker;
import org.example.ratingsneakerapp.admin.SneakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private SneakersRepository sneakersRepository;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {

        List<Sneaker> sneakers = sneakersRepository.findTop5ByOrderByRatingDesc();
        model.addAttribute("sneakers", sneakers);
        return jwtService.GetRoleByJwt(request, model, "index");
    }
}
