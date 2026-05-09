package com.stackoverflow.entities;

import com.stackoverflow.enums.VoteType;

public class Vote {
	private User user;
	private VoteType type;

	public Vote(User user, VoteType type) {
		this.user = user;
		this.type = type;
	}
}
