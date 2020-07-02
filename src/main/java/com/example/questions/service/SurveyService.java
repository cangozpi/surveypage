package com.example.questions.service;

import com.example.questions.dao.QuestionRepository;
import com.example.questions.dao.SurveyRepository;
import com.example.questions.model.QuestionModel;
import com.example.questions.model.SurveyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    // to add a single SurveyModel to db
    public void postSurvey(SurveyModel surveyModel){
        surveyRepository.save(surveyModel);
    }

    //to get All of the available SurveyModels in the db
    public List<SurveyModel> getAllSurvey() {
        return surveyRepository.findAll();

    }

    public Optional<SurveyModel> getSurvey(String id) {
        return surveyRepository.findById(id);
    }

    public List<String> getAllSurveyNames(List<SurveyModel> surveys){
        List<String> surveyNames = new ArrayList<>();
        for(int i=0; i< surveys.size() ; i++){
            surveyNames.add(surveys.get(i).getName());
        }
        return surveyNames;
    }

    public boolean deleteSurveyById(String id) {
        if(surveyRepository.findById(id) != null){
            surveyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //handless Save Btn of New Survey Page
    public void postNewSurvey(String name, List<String> checkboxIds) {
        List<QuestionModel> questions= new ArrayList<>();

        for(String checkboxId : checkboxIds){
            QuestionModel questionModel = new QuestionModel(name,null,null);
            questionModel.setId(checkboxId);
            questions.add(questionModel);
        }

        SurveyModel surveyModel = new SurveyModel(name,questions);
        surveyRepository.save(surveyModel);

    }

    public void EditSurvey(String surveyId, String name, List<String> checkboxId) {
        if(surveyRepository.findById(surveyId)!= null){// if it already exists
            List<QuestionModel> questions = new ArrayList<>();

            //Form List<QuestionModel> which is to be passed into setQuestions();
            for(String questionId: checkboxId){
                QuestionModel tempQuestionModel = new QuestionModel(name,null,null);
                tempQuestionModel.setId(questionId);
                questions.add(tempQuestionModel);
            }

            //remove old survey
            surveyRepository.deleteById(surveyId);

            //create and add new survey
            SurveyModel surveyModel = new SurveyModel(name, questions);
            surveyModel.setId(surveyId);
            surveyRepository.save(surveyModel);


        }else{
            System.out.println("something went wrong while editing the survey !");
        }
    }
}
