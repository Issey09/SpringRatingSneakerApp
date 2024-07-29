package org.example.ratingsneakerapp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class EditSneakerController {

    @Autowired
    private SneakersRepository sneakersRepository;

    // Обработка поиска кроссовка по ID
    @GetMapping("/sneaker/find")
    public String findSneaker(Model model) {
        model.addAttribute("sneaker", new Sneaker());
        return "admin/edit_sneaker/sneakerFind";
    }

    @PostMapping("/sneaker/find")
    public String findSneakerById(@ModelAttribute("sneaker") Sneaker sneaker, Model model) {// Передаем найденный кроссовок на страницу редактирования
        return "redirect:/admin/sneaker/edit"; // изменил путь, чтобы корректно перенаправлять
    }

    // Отображение страницы редактирования
    @GetMapping("/sneaker/edit")
    public String editSneaker(Model model) {
        model.addAttribute("sneaker1", new Sneaker());
        return "admin/edit_sneaker/editSneaker";
    }

    // Обновление данных кроссовка
    @PostMapping("/sneaker/edit")
    public String updateSneaker(@ModelAttribute("sneaker1") Sneaker sneaker1, @ModelAttribute("sneaker") Sneaker sneaker, Model model) {
        Sneaker sneakerFound = sneakersRepository.findById(sneaker.getId());
        if (sneakerFound != null) {
            model.addAttribute("error", "Sneaker not found");
            return "admin/edit_sneaker/editSneaker";
        } else {
            sneakerFound.setBrand(sneaker1.getBrand());
            sneakerFound.setColor(sneaker1.getColor());
            sneakerFound.setModel(sneaker1.getModel());
            sneakerFound.setPrice(sneaker1.getPrice());
            sneakerFound.setPhotoUrl(sneaker1.getPhotoUrl());
            sneakersRepository.save(sneakerFound);
            return "admin/create_sneakers/success";
        }
    }
}


