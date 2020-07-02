package com.example.questions.model;

import org.springframework.data.annotation.Id;

public class QuestionAnswerModel {

    private String name;
    private String answer;

    public QuestionAnswerModel(String name, String answer){
        this.name = name;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionAnswerModel{" +
                "name='" + name + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
