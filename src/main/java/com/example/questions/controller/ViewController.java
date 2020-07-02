package com.example.questions.controller;

import com.example.questions.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ViewService viewService;


    //Serves SurveyCreation.jsp
    @GetMapping("/surveyCreation")
    public String surveyCreationServeJSP(@RequestParam String id, Model model){
        model.addAttribute("surveyName", viewService.getAnsweredSurveyById(id).getSurveyName());
        model.addAttribute("questions", viewService.getAnsweredSurveyById(id).getQuestions());
        return "surveyCreation";
    }

    //Handles excel
    @GetMapping("/excelTablePage")
    public String excelTablePageJSP(@RequestParam List<String> ids, Model model){
        model.addAttribute("ids",viewService.getAnsweredSurveyModelsbyId(ids));
        return "excelTablePage";
    }
    
}
