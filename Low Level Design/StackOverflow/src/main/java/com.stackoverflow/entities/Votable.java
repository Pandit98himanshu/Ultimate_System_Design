package com.stackoverflow.entities;

import java.util.List;

import enums.VoteType;

public interface Votable {
	public void vote(User voter, VoteType type);
	public List<Vote> getVotes();
}
