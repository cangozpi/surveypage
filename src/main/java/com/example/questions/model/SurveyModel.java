package com.example.questions.model;

import com.example.questions.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Optional;


public class SurveyModel {
    @Id
    private String id;
    private String name;
    private List<QuestionModel> questions;
    private String userName;

    public SurveyModel(String name, List<QuestionModel> questions){
        this.name=name;
        this.questions=questions;
        this.userName = "";
        //Fill questionsList using allcheckedIds array
        /*
        for(int i=0; i< allCheckedIds.length;i++){
            String currentId = allCheckedIds[i];
            if(questionRepository.findById(currentId)!=null){// if questionModel with that id exists
                questionsList.add(questionRepository.findById(currentId));
            }
        }*/

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SurveyModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
