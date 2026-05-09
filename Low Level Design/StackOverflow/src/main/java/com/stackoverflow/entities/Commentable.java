package com.stackoverflow.entities;

import java.util.List;

public interface Commentable {
	void comment(User commentor, Comment comment);
	List<Comment> getComments();
}
