package com.example.questions.dao;

import com.example.questions.model.QuestionModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel, String> {
        public QuestionModel findById(@Param("id") Id id);

}
