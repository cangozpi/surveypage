package com.example.questions.dao;

import com.example.questions.model.ApplicationUserModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUserModel,String> {
    public ApplicationUserModel findByUserName(@Param("userName")String userName);
    public void deleteByUserName(@Param("userName") String userName);
}
