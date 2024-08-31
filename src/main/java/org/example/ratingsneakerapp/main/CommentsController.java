package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentsController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addComment(HttpServletRequest request, @RequestBody CreateComment comment) {
        String username = userService.findUser(request);

        Comment savedComment = new Comment();
        savedComment.setContent(comment.getContent());
        savedComment.setAuthor(username);
        savedComment.setSneakersId(comment.getSneakerId());
        commentRepository.save(savedComment);



        return ResponseEntity.ok().body(comment);
    }

    @GetMapping("/get")
    public List<Comment> getComments(@RequestParam int id) {
        return commentRepository.findBySneakersId(id);
    }
}
