package com.stackoverflow.entities;

import java.util.List;

import com.stackoverflow.enums.VoteType;

public interface Votable {
	void vote(User voter, VoteType type);
	List<Vote> getVotes();
}
