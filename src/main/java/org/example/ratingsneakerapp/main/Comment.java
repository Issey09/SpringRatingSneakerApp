package org.example.ratingsneakerapp.main;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private String author;
    private int sneakers_id;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
