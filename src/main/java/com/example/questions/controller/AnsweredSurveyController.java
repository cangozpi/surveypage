package com.example.questions.controller;

import com.example.questions.model.AnsweredSurveyModel;
import com.example.questions.service.AnsweredSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
public class AnsweredSurveyController {

    @Autowired
    AnsweredSurveyService answeredSurveyService;

    //Handles post operation of answerSurvey form
    @PostMapping("/saveSurveyResults")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public RedirectView saveSurveyResults(@RequestParam Map<String,String> questions){
        answeredSurveyService.answeredSurveyModel(questions);
        return new RedirectView("saveSurveyResults.html");
    }

    //to get all of the AnsweredSurveyModel's that are in the db
    @GetMapping("/getAllAnsweredSurveys")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SURVEYCREATOR')")
    public List<AnsweredSurveyModel> getAllSurvey(){
        return answeredSurveyService.getAllAnsweredSurveys();
    }

    

}
