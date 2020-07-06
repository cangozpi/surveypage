package com.example.questions.service;

import com.example.questions.dao.AnsweredSurveyRepository;
import com.example.questions.model.AnsweredSurveyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewService {

    @Autowired
    AnsweredSurveyRepository answeredSurveyRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public AnsweredSurveyModel getAnsweredSurveyById(String id){
        return answeredSurveyRepository.findById(id).get();

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SURVEYCREATOR')")
    public List<AnsweredSurveyModel> getAnsweredSurveyModelsbyId(List<String> ids) {
        List<AnsweredSurveyModel> modelList = new ArrayList<>();
        for(String id: ids){
            modelList.add(answeredSurveyRepository.findById(id).get());
        }
        return modelList;
    }
}
