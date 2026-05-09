package com.stackoverflow.entities;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Post implements Votable, Commentable {
	private int id;
	private int content;
	private User author;
	private LocalDateTime creationDateTime;
	private List<Comment> comments;
	private List<Vote> votes;
}
