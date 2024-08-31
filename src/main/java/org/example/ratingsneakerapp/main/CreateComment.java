package org.example.ratingsneakerapp.main;

import lombok.Data;

@Data
public class CreateComment {
    private String content;
    private int sneakerId;
    private String author;
}
