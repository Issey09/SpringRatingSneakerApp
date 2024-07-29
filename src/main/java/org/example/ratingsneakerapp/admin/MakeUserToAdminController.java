package org.example.ratingsneakerapp.admin;

import org.example.ratingsneakerapp.main.Role;
import org.example.ratingsneakerapp.main.User;
import org.example.ratingsneakerapp.main.UserDetailImpl;
import org.example.ratingsneakerapp.main.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MakeUserToAdminController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/make/admin")
    public String makeAdmin(Model model) {
        model.addAttribute("user", new User());
        return "admin/make_admin/make_admin";

    }
    @PostMapping("/make/admin")
    public String makeAdmin2(Model model, @ModelAttribute("user")User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            model.addAttribute("error", "error");
            return "admin/make_admin/make_admin";
        }else {
            foundUser.setRole(Role.ADMIN);
            userRepository.save(foundUser);
            return "admin/make_admin/sucsess";
        }

    }

}
