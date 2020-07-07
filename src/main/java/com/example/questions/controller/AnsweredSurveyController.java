package com.example.questions.controller;

import com.example.questions.model.AnsweredSurveyModel;
import com.example.questions.service.AnsweredSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AnsweredSurveyController {

    @Autowired
    AnsweredSurveyService answeredSurveyService;

    //Handles post operation of answerSurvey form
    @PostMapping("/saveSurveyResults")
    public RedirectView saveSurveyResults(@RequestParam Map<String,String> questions, HttpServletRequest request, @RequestParam("userName") String userName){

        if(userName.equals(null)){
            String userNameRequest = request.getUserPrincipal().getName();
            answeredSurveyService.answeredSurveyModel(questions, userNameRequest);
        }else{
            answeredSurveyService.answeredSurveyModel(questions, userName);
        }

        return new RedirectView("answerSaved.html");
    }

    //to get all of the AnsweredSurveyModel's that are in the db
    @GetMapping("/getAllAnsweredSurveys")
    @ResponseBody
    public List<AnsweredSurveyModel> getAllSurvey(HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return answeredSurveyService.getAllAnsweredSurveys(userName);
    }

    

}
