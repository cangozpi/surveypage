package com.example.questions.service;

import com.example.questions.dao.QuestionRepository;
import com.example.questions.dao.SurveyRepository;
import com.example.questions.model.QuestionModel;
import com.example.questions.model.SurveyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    // to add a single SurveyModel to db
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void postSurvey(SurveyModel surveyModel, String userName){
        surveyModel.setUserName(userName);
        surveyRepository.save(surveyModel);
    }

    //to get All of the available SurveyModels in the db
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public List<SurveyModel> getAllSurvey(String userName) {
        return surveyRepository.findAll().stream().filter(x -> x.getUserName().equals(userName)).collect(Collectors.toList());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public Optional<SurveyModel> getSurvey(String id, String userName) {
        SurveyModel s = surveyRepository.findById(id).get();
        if(s.getUserName().equals(userName)){
            return surveyRepository.findById(id);
        }
        return null;
    }

    public List<String> getAllSurveyNames(List<SurveyModel> surveys){
        List<String> surveyNames = new ArrayList<>();
        for(int i=0; i< surveys.size() ; i++){
            surveyNames.add(surveys.get(i).getName());
        }
        return surveyNames;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public boolean deleteSurveyById(String id, String userName) {
        if(surveyRepository.findById(id) != null && surveyRepository.findById(id).get().getUserName().equals(userName)){
            surveyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //handless Save Btn of New Survey Page
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void postNewSurvey(String name, List<String> checkboxIds, String userName) {
        List<QuestionModel> questions= new ArrayList<>();

        for(String checkboxId : checkboxIds){
            QuestionModel questionModel = new QuestionModel(name,null,null);
            questionModel.setId(checkboxId);
            questions.add(questionModel);
        }

        SurveyModel surveyModel = new SurveyModel(name,questions);
        surveyModel.setUserName(userName);
        surveyRepository.save(surveyModel);

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void EditSurvey(String surveyId, String name, List<String> checkboxId, String userName) {
        if(surveyRepository.findById(surveyId)!= null && surveyRepository.findById(surveyId).get().getUserName().equals(userName)){// if it already exists
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
            surveyModel.setUserName(userName);
            surveyRepository.save(surveyModel);


        }else{
            System.out.println("something went wrong while editing the survey !");
        }
    }

    public Optional<SurveyModel> getSurveyOnlyById(String id) {
        return surveyRepository.findById(id);
    }
}
