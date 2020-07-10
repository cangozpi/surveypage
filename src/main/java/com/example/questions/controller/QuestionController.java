package com.example.questions.controller;

import com.example.questions.model.QuestionModel;
import com.example.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController   {


    @Autowired
    QuestionService questionService;

    //makes a post request to http://localhost:8080/questions/newQuestion
    @PostMapping("/newQuestion")
    public RedirectView postQuestion(@ModelAttribute QuestionModel question, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        questionService.createQuestion(question, userName);
        return new RedirectView("/questions.html");
    }

    @GetMapping("/newQuestion")
    public List<QuestionModel> getAllQuestions(HttpServletRequest request){
        List<QuestionModel> questions = questionService.getAllQuestions();
        String userName = request.getUserPrincipal().getName();
        return questions.stream().filter(questionModel -> questionModel.getUserName().equals(userName)).collect(Collectors.toList());
    }

    @DeleteMapping("/newQuestion")
    public void deleteAllQuestions(HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        questionService.deleteAllQuestions(userName);
    }

    @DeleteMapping("/deleteById")
    public boolean deleteById(@RequestBody String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        return questionService.deleteById(id.split("\"")[3].split("\"")[0], userName);
    }

    //Used for sending all of the Object's info to JS for Edit Button Option
    @GetMapping("/getQuestionById")
    @ResponseBody
    public Optional<QuestionModel> getQuestionById(@RequestParam("id") String id, HttpServletRequest request,@RequestParam("userName") String userName){
        if(userName.equals("null")){
            String userNameRequest = request.getUserPrincipal().getName();
            return questionService.getQuestionById(id, userNameRequest);
        }else {
            return questionService.getQuestionById(id, userName);
        }

    }

    //used for UPDATING already existing questionModel Objects
    @PostMapping("/updateById")
    public RedirectView updateById(@ModelAttribute QuestionModel question, @RequestParam String id, HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        questionService.updateById(question,id, userName);
        return new RedirectView("/questions.html");
    }
    @PostMapping("/can")
    public String deneme(){
        return "aa";
    }


}
