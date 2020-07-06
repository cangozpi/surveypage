
package com.example.questions.service;

import com.example.questions.dao.QuestionRepository;
import com.example.questions.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public boolean createQuestion(QuestionModel question) {
        repository.save(question);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void deleteAllQuestions(){
        repository.deleteAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public List<QuestionModel> getAllQuestions() {
        return repository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public boolean deleteById(String id){
        repository.deleteById(id);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public Optional<QuestionModel> getQuestionById(String id){
        return repository.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void updateById(QuestionModel question, String id) {
        repository.deleteById(id);
        repository.save(question);
    }
}
