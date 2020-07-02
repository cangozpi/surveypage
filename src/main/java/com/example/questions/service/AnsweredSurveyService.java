package com.example.questions.service;

import com.example.questions.dao.AnsweredSurveyRepository;
import com.example.questions.model.AnsweredSurveyModel;
import com.example.questions.model.QuestionAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnsweredSurveyService {

    @Autowired
    AnsweredSurveyRepository answeredSurveyRepository;

    public void answeredSurveyModel(Map<String, String> questions) {
        //name of the survey
        String surveyName = questions.get("surveyName");
        questions.remove("surveyName"); // remove so that dont iterate and add it to questionList later on

        //List<QuestionAnswerModel> for passing to constructor
        List<QuestionAnswerModel> questionList = new ArrayList<>();

        //Iterate over the questions
        questions.forEach((key, value) -> {
            questionList.add(new QuestionAnswerModel(key, value));
        });

        answeredSurveyRepository.save(new AnsweredSurveyModel(surveyName, questionList));

    }

    public List<AnsweredSurveyModel> getAllAnsweredSurveys() {
        return answeredSurveyRepository.findAll();
    }
}
