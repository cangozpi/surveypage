package com.example.questions.controller;

import com.example.questions.model.QuestionModel;
import com.example.questions.model.SurveyModel;
import com.example.questions.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SurveyController {


    @Autowired
    SurveyService surveyService;


    //to save a SurveyModel to db
    @PostMapping("/postSurvey")
    public void postSurvey(@RequestParam String name, @RequestParam String [] questionIds, HttpServletRequest request){

        List<QuestionModel> questions = new ArrayList<>();
        for(String questionId : questionIds) {
            QuestionModel question = new QuestionModel(null, null,null);
            question.setId(questionId);
            questions.add(question);
        }

        SurveyModel survey = new SurveyModel(name, questions);

        String userName = request.getUserPrincipal().getName();
        surveyService.postSurvey(survey, userName);
    }

    //to get all of the SurveyModel's that are in the db
    @GetMapping("/getAllSurveys")
    @ResponseBody
    public List<SurveyModel> getAllSurvey(HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return surveyService.getAllSurvey(userName);
    }

    @GetMapping("/getSurvey")
    public Optional<SurveyModel> getSurvey(@RequestParam String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return surveyService.getSurvey(id, userName);
    }

    @DeleteMapping("/deleteSurveyById")
    @ResponseBody
    public boolean deleteSurveyById(@RequestParam String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return surveyService.deleteSurveyById(id, userName);
    }

    //New survey POST form submission
    @PostMapping("/newSurvey")
    public RedirectView postNewSurvey(@RequestParam  String name, @RequestParam List<String> checkboxId, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        surveyService.postNewSurvey(name, checkboxId, userName);
        return new RedirectView("/createSurvey.html");
    }

    //Edit survey retrieving prior survey data
    @GetMapping("/editSurvey")
    @ResponseBody
    public Optional<SurveyModel> editSurvey(@RequestParam String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return surveyService.getSurvey(id, userName);
    }

    //Edit Survey
    @PostMapping("/editSurvey")
    public RedirectView editSurvey(@RequestParam  String name, @RequestParam List<String> checkboxId, @RequestParam String surveyId, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        surveyService.EditSurvey(surveyId, name, checkboxId, userName);
        return new RedirectView("/createSurvey.html");
    }

    //Edit survey retrieving prior survey data
    @GetMapping("/answerSurvey")
    @ResponseBody
    public Optional<SurveyModel> answerSurvey(@RequestParam String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return surveyService.getSurvey(id, userName);
    }


}