package com.example.questions.controller;

import com.example.questions.model.QuestionModel;
import com.example.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController   {


    @Autowired
    QuestionService questionService;

    //makes a post request to http://localhost:8080/questions/newQuestion
    @PostMapping("/newQuestion")
    public RedirectView postQuestion(@ModelAttribute QuestionModel question){
        questionService.createQuestion(question);
        return new RedirectView("/questions.html");
    }

    @GetMapping("/newQuestion")
    public List<QuestionModel> getAllQuestions(){
        List<QuestionModel> questions = questionService.getAllQuestions();
        return questions;
    }

    @DeleteMapping("/newQuestion")
    public void deleteAllQuestions(){
        questionService.deleteAllQuestions();
    }

    @DeleteMapping("/deleteById")
    public boolean deleteById(@RequestBody String id){
        return questionService.deleteById(id.split("\"")[3].split("\"")[0]);
    }

    //Used for sending all of the Object's info to JS for Edit Button Option
    @GetMapping("/getQuestionById")
    @ResponseBody
    public Optional<QuestionModel> getQuestionById(@RequestParam String id){
        return questionService.getQuestionById(id);
    }

    //used for UPDATING already existing questionModel Objects
    @PostMapping("/updateById")
    public RedirectView updateById(@ModelAttribute QuestionModel question, @RequestParam String id){
        questionService.updateById(question,id);
        return new RedirectView("/questions.html");
    }

}
