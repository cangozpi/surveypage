package com.example.questions.controller;

import com.example.questions.model.QuestionModel;
import com.example.questions.model.SurveyModel;
import com.example.questions.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SurveyController {


    @Autowired
    SurveyService surveyService;


    //to save a SurveyModel to db
    @PostMapping("/postSurvey")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public void postSurvey(@RequestParam String name, @RequestParam String [] questionIds){

        List<QuestionModel> questions = new ArrayList<>();
        for(String questionId : questionIds) {
            QuestionModel question = new QuestionModel(null, null,null);
            question.setId(questionId);
            questions.add(question);
        }

        SurveyModel survey = new SurveyModel(name, questions);
        surveyService.postSurvey(survey);
    }

    //to get all of the SurveyModel's that are in the db
    @GetMapping("/getAllSurveys")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public List<SurveyModel> getAllSurvey(){
        return surveyService.getAllSurvey();
    }

    @GetMapping("/getSurvey")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public Optional<SurveyModel> getSurvey(@RequestParam String id){
        return surveyService.getSurvey(id);
    }

    @DeleteMapping("/deleteSurveyById")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public boolean deleteSurveyById(@RequestParam String id){
        return surveyService.deleteSurveyById(id);
    }

    //New survey POST form submission
    @PostMapping("/newSurvey")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public RedirectView postNewSurvey(@RequestParam  String name, @RequestParam List<String> checkboxId){
        surveyService.postNewSurvey(name, checkboxId);
        return new RedirectView("/createSurvey.html");
    }

    //Edit survey retrieving prior survey data
    @GetMapping("/editSurvey")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public Optional<SurveyModel> editSurvey(@RequestParam String id){
        return surveyService.getSurvey(id);
    }

    //Edit Survey
    @PostMapping("/editSurvey")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public RedirectView editSurvey(@RequestParam  String name, @RequestParam List<String> checkboxId, @RequestParam String surveyId){
        surveyService.EditSurvey(surveyId, name, checkboxId);
        return new RedirectView("/createSurvey.html");
    }

    //Edit survey retrieving prior survey data
    @GetMapping("/answerSurvey")
    @ResponseBody
    public Optional<SurveyModel> answerSurvey(@RequestParam String id){
        return surveyService.getSurvey(id);
    }


}