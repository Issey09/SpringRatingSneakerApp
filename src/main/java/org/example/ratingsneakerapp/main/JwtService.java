package org.example.ratingsneakerapp.main;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class JwtService {
    @Value("${spring.app.secret}")
    private String secret;
    public String GetRoleByJwt(HttpServletRequest request, Model model, String html) {
        String jwt = null;
        String role = null;

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            jwt = authorizationHeader.substring(7);
            Claims claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(jwt)
                        .getBody();

            role = claims.getSubject();
            if (role.equals("ADMIN")) {
                model.addAttribute("admin", "");
                return html;
            }
            if (role.equals("USER")) {
                model.addAttribute("user", "");
                return html;
            }else {
                return html;
            }
        }else {
            return html;
        }
    }
}
