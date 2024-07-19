package org.example.ratingsneakerapp.admin;

import org.example.ratingsneakerapp.admin.SneakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/")
public class CresteSneakersAdminController {
    @Autowired
    private SneakersRepository sneakersRepository;

    @GetMapping("/create/sneakers")
    public String createSneakers(Model model) {
        model.addAttribute("sneakers", new Sneaker());
        return "admin/create_sneakers/create_sneakers";
    }
    @PostMapping("/create/sneakers")
    public String createSneakers2(@ModelAttribute("sneakers")Sneaker sneaker, Model model) {
        if (sneakersRepository.findByBrand(sneaker.getBrand()) == null) {
            sneakersRepository.save(sneaker);
        return "admin/create_sneakers/sucsess";
        }else {
            model.addAttribute("error", "Something went wrong");
            return "admin/create_sneakers/create_sneakers";
        }


    }

}
