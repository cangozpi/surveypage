
package com.example.questions.service;

import com.example.questions.dao.QuestionRepository;
import com.example.questions.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public boolean createQuestion(QuestionModel question) {
        repository.save(question);
        return true;
    }

    public void deleteAllQuestions(){
        repository.deleteAll();
    }

    public List<QuestionModel> getAllQuestions() {
        return repository.findAll();
    }

    public boolean deleteById(String id){
        repository.deleteById(id);
        return true;
    }

    public Optional<QuestionModel> getQuestionById(String id){
        return repository.findById(id);
    }

    public void updateById(QuestionModel question, String id) {
        repository.deleteById(id);
        repository.save(question);
    }
}
