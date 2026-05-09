package com.stackoverflow.entities;

import java.util.List;

public class Question extends Post {
	private String title;
	private List<Answer> answers;
	private List<Tag> tags;
}
