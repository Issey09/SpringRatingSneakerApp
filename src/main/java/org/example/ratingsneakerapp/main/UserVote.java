package org.example.ratingsneakerapp.main;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_votes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username", "sneaker_id"})
})
@Data
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "sneaker_id", nullable = false)
    private Long sneakerId;

    @Column(name = "vote_type", nullable = false)
    private String voteType; // "like" или "dislike"

    // Getters и Setters
    // ...
}