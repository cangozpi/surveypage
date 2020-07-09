package com.example.questions.controller;

import com.example.questions.model.ApplicationUserModel;
import com.example.questions.model.QuestionAnswerModel;
import com.example.questions.service.ApplicationUserService;
import com.example.questions.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    @Autowired
    ViewService viewService;

    @Autowired
    ApplicationUserService applicationUserService;


    //Serves SurveyCreation.jsp
    @GetMapping("/surveyCreation")
    public String surveyCreationServeJSP(@RequestParam String id, Model model){
        model.addAttribute("surveyName", viewService.getAnsweredSurveyById(id).getSurveyName());
        List<QuestionAnswerModel>questions = viewService.getAnsweredSurveyById(id).getQuestions().stream().filter(x -> (x.getName().equalsIgnoreCase("userName") == false)).collect(Collectors.toList());
        model.addAttribute("questions", questions);
        return "surveyCreation";
    }

    //Handles excel
    @GetMapping("/excelTablePage")
    public String excelTablePageJSP(@RequestParam List<String> ids, Model model){
        model.addAttribute("ids",viewService.getAnsweredSurveyModelsbyId(ids));
        return "excelTablePage";
    }

    //Handles sign in page
    @GetMapping("/register")
    public String signInPageJSP(){
        return "signin";
    }

    @GetMapping("/")
    public String indexPageJSP(Model model, HttpServletRequest request, Authentication auth){
        if(auth != null) {
            model.addAttribute("user", (ApplicationUserModel) auth.getPrincipal());
        }
        return "index";
    }

    //Handles Admin Console JSP rendering

    @GetMapping("/adminConsole")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminConsolePageJSP(Model model){
        List<ApplicationUserModel> users = applicationUserService.getAllUsers();
        model.addAttribute("users", users);
        return "adminConsole";
    }

}