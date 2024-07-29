package org.example.ratingsneakerapp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class EditSneakerListController {
    @Autowired
    private SneakersRepository sneakersRepository;
    @GetMapping("/edit/list")
    public String list(@RequestParam(value = "brand", required = false) String brand,
                                 @RequestParam(value = "model", required = false) String model,
                                 Model modelAttr) {
        List<Sneaker> filteredSneakers;

        if (brand != null && !brand.isEmpty() && model != null && !model.isEmpty()) {
            filteredSneakers = sneakersRepository.findByBrandAndModel(brand, model);
        } else if (brand != null && !brand.isEmpty()) {
            filteredSneakers = sneakersRepository.findByBrand(brand);
        } else if (model != null && !model.isEmpty()) {
            filteredSneakers = sneakersRepository.findByModel(model);
        } else {
            filteredSneakers = sneakersRepository.findAll();
        }

        modelAttr.addAttribute("sneakers", filteredSneakers);
         // Убедитесь, что у вас есть шаблон "sneaker/all.html"
        return "admin/edit_sneaker/editList";
    }
    @PostMapping("/edit/{id}")
    public String dislike(@PathVariable int id, Model model) {
        Sneaker sneaker = sneakersRepository.findById(id);
        sneakersRepository.delete(sneaker);
        return "redirect:/admin/edit/list";
    }
}
