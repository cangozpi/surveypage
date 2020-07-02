package com.example.questions.dao;

import com.example.questions.model.AnsweredSurveyModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface AnsweredSurveyRepository extends MongoRepository<AnsweredSurveyModel, String> {
    public AnsweredSurveyModel findById(@Param("id") Id id);
}
