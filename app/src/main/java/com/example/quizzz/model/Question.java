package com.example.quizzz.model;

import java.util.List;

public class Question {

    private final String mQuestion;
    private final List<String> mChoicelist;
    private final int mAnswerIndex;

    public Question(String question, List<String> choicelist, int answerIndex) {
        mQuestion = question;
        mChoicelist = choicelist;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoicelist() {
        return mChoicelist;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
}
