package com.stackoverflow;

import com.stackoverflow.entities.Question;
import com.stackoverflow.entities.User;

import java.util.ArrayList;
import java.util.List;

public class StackOverflow {
    private final List<User> users = new ArrayList<>();
    private final List<Question> questions = new ArrayList<>();
}
