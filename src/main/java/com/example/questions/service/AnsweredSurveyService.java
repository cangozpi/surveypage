package com.example.questions.service;

import com.example.questions.dao.AnsweredSurveyRepository;
import com.example.questions.model.AnsweredSurveyModel;
import com.example.questions.model.QuestionAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnsweredSurveyService {

    @Autowired
    AnsweredSurveyRepository answeredSurveyRepository;


    public void answeredSurveyModel(Map<String, String> questions, String userName) {
        //name of the survey
        String surveyName = questions.get("surveyName");
        questions.remove("surveyName"); // remove so that dont iterate and add it to questionList later on

        //List<QuestionAnswerModel> for passing to constructor
        List<QuestionAnswerModel> questionList = new ArrayList<>();

        //Iterate over the questions
        questions.forEach((key, value) -> {
            questionList.add(new QuestionAnswerModel(key, value));
        });

        AnsweredSurveyModel model = new AnsweredSurveyModel(surveyName, questionList);
        model.setUserName(userName);
        answeredSurveyRepository.save(model);

    }
    public List<AnsweredSurveyModel> getAllAnsweredSurveys(String userName) {
        return answeredSurveyRepository.findAll().stream().filter(x -> x.getUserName().equals(userName)).collect(Collectors.toList());
    }
}
