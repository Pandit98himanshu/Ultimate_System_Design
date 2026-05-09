package com.stackoverflow.entities;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private User author;
    private LocalDateTime creationDateTime;
}
