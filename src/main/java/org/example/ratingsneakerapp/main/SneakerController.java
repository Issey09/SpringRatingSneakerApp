package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ratingsneakerapp.admin.Sneaker;
import org.example.ratingsneakerapp.admin.SneakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sneaker")
public class SneakerController {
    @Autowired
    private SneakersRepository sneakersRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/detail")
    public Sneaker getSneakerById(@RequestParam int id) {
    return sneakersRepository.findById(id);
    }



    @PostMapping("/like")
    public String likeSneaker(@RequestBody SneakerVoteRequest request) {
    Long id = request.getId();
    String username = request.getUsername();

    Optional<UserVote> existingVote = userVoteRepository.findByUsernameAndSneakerId(username, id);
    if (!existingVote.isPresent()) {
        Optional<Sneaker> sneaker = sneakersRepository.findById(id);
        if (sneaker.isPresent()) {
            Sneaker s = sneaker.get();
            s.setLikes(s.getLikes() + 1);
            sneakersRepository.save(s);

            UserVote userVote = new UserVote();
            userVote.setUsername(username);
            userVote.setSneakerId(id);
            userVote.setVoteType("like");
            userVoteRepository.save(userVote);
        }
    }
    return "Vote counted";
}

    @PostMapping("/dislike")
    public String dislikeSneaker(@RequestBody SneakerVoteRequest request) {
    Long id = request.getId();
    String username = request.getUsername();

    Optional<UserVote> existingVote = userVoteRepository.findByUsernameAndSneakerId(username, id);
    if (!existingVote.isPresent()) {
        Optional<Sneaker> sneaker = sneakersRepository.findById(id);
        if (sneaker.isPresent()) {
            Sneaker s = sneaker.get();
            s.setDislikes(s.getDislikes() + 1);
            sneakersRepository.save(s);

            UserVote userVote = new UserVote();
            userVote.setUsername(username);
            userVote.setSneakerId(id);
            userVote.setVoteType("dislike");
            userVoteRepository.save(userVote);
        }
    }
    return "Vote counted";
}

    @GetMapping("/all")
    public List<Sneaker> filterSneakers(@RequestParam(value = "brand", required = false) String brand,
                                        @RequestParam(value = "model", required = false) String model) {
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
        return filteredSneakers;

    }
}
























