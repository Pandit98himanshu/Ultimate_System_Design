package com.stackoverflow.entities;

import java.util.List;

public interface Commentable {
	public void comment(User commentor, Comment comment);
	public List<Comment> getComments(Post post);
}
