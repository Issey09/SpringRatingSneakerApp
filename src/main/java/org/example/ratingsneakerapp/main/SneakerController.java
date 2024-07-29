package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ratingsneakerapp.admin.Sneaker;
import org.example.ratingsneakerapp.admin.SneakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sneaker")
public class SneakerController {
    @Autowired
    private SneakersRepository sneakersRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/{id}")
    public String getSneakerById(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Sneaker> sneaker = sneakersRepository.findById(id);
        if (sneaker.isPresent()) {
            model.addAttribute("sneaker", sneaker.get());
            return jwtService.GetRoleByJwt(request, model, "sneaker/detail");
        }
        return jwtService.GetRoleByJwt(request, model, "error");
    }

    @PostMapping("/{id}/like")
    public String likeSneaker(@PathVariable Long id, @RequestParam Long userId, HttpServletRequest request, Model model) {
        Optional<UserVote> existingVote = userVoteRepository.findByUserIdAndSneakerId(userId, id);
        if (!existingVote.isPresent()) {
            Optional<Sneaker> sneaker = sneakersRepository.findById(id);
            if (sneaker.isPresent()) {
                Sneaker s = sneaker.get();
                s.setLikes(s.getLikes() + 1);
                sneakersRepository.save(s);

                UserVote userVote = new UserVote();
                userVote.setUserId(userId);
                userVote.setSneakerId(id);
                userVote.setVoteType("like");
                userVoteRepository.save(userVote);
            }
        }
        return jwtService.GetRoleByJwt(request, model, "redirect:/sneaker/" + id);
    }
    @GetMapping("/all")
    public String filterSneakers(@RequestParam(value = "brand", required = false) String brand,
                                 @RequestParam(value = "model", required = false) String model,
                                 Model modelAttr, HttpServletRequest request) {
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
        return jwtService.GetRoleByJwt(request, modelAttr, "sneaker/all");
    }

    @PostMapping("/{id}/dislike")
    public String dislikeSneaker(@PathVariable Long id, @RequestParam Long userId, HttpServletRequest request, Model model) {
        Optional<UserVote> existingVote = userVoteRepository.findByUserIdAndSneakerId(userId, id);
        if (!existingVote.isPresent()) {
            Optional<Sneaker> sneaker = sneakersRepository.findById(id);
            if (sneaker.isPresent()) {
                Sneaker s = sneaker.get();
                s.setDislikes(s.getDislikes() + 1);
                sneakersRepository.save(s);

                UserVote userVote = new UserVote();
                userVote.setUserId(userId);
                userVote.setSneakerId(id);
                userVote.setVoteType("dislike");
                userVoteRepository.save(userVote);
            }
        }
        return jwtService.GetRoleByJwt(request, model, "redirect:/sneaker/" + id);
    }
}
























