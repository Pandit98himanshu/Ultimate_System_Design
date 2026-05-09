package com.stackoverflow.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.stackoverflow.enums.VoteType;

public abstract class Post implements Votable, Commentable {
	private int id;
	private String content;
	private User author;
	private LocalDateTime creationDateTime = LocalDateTime.now();
	private final List<Comment> comments = new ArrayList<>();
	private final List<Vote> votes = new ArrayList<>();

	@Override
	public void vote(User voter, VoteType type) {
		votes.add(new Vote(voter, type));
	}

	@Override
	public List<Vote> getVotes() {
		return votes;
	}

	@Override
	public void comment(User commentor, Comment comment) {
		comments.add(comment);
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}
}
