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

}


