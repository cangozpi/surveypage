package com.example.questions.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class AnsweredSurveyModel {

    @Id
    private String id;

    private String surveyName;
    private List<QuestionAnswerModel> questions;

    public AnsweredSurveyModel(String surveyName,  List<QuestionAnswerModel> questions){
        this.surveyName = surveyName;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public List<QuestionAnswerModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAnswerModel> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "AnsweredSurveyModel{" +
                "id='" + id + '\'' +
                ", surveyName='" + surveyName + '\'' +
                ", questions=" + questions +
                '}';
    }
}
