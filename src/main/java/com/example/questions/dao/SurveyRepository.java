package com.example.questions.dao;

import com.example.questions.model.SurveyModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends MongoRepository<SurveyModel,String> {
    public Optional<SurveyModel> findById(@Param("id") Id id);
}
