package org.example.ratingsneakerapp.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class MainAdminController {
    @GetMapping("/")
    public String createSneakers() {
        return "admin/index";
    }
}
