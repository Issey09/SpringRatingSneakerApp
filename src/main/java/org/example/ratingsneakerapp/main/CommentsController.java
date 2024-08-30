package org.example.ratingsneakerapp.main;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/add/comments")
    public ResponseEntity<?> addComment(HttpServletRequest request, @RequestBody Comment comment) {
        String username = userService.findUser(request);

        comment.setComment(comment.getComment());
        comment.setAuthor(username);
        comment.setSneakers_id(comment.getSneakers_id());

        commentRepository.save(comment);

        return ResponseEntity.ok().body(comment);
    }
}
