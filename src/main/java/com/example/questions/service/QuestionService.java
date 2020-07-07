
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
    public boolean createQuestion(QuestionModel question, String userName) {
       question.setUserName(userName);
        repository.save(question);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void deleteAllQuestions(String userName){
        repository.findAll().stream().filter(x -> x.getUserName().equals(userName)).forEach(x -> repository.deleteById(x.getId()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public List<QuestionModel> getAllQuestions() {
        return repository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public boolean deleteById(String id, String userName){
        Optional<QuestionModel> q = repository.findById(id);
        if(userName.equals(q.get().getUserName())){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Optional<QuestionModel> getQuestionById(String id, String userName){
        QuestionModel q = repository.findById(id).get();

        if(userName.equals(q.getUserName())){
            return repository.findById(id);
        }
        return null;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public void updateById(QuestionModel question, String id, String userName) {
        QuestionModel q = repository.findById(id).get();
        if(q.getUserName().equals(userName)){
            repository.deleteById(id);
            repository.save(question);
        }
    }
}
