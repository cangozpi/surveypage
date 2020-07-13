package com.example.questions.service;

import com.example.questions.dao.ApplicationUserRepository;
import com.example.questions.model.ApplicationUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ApplicationUserModel user = applicationUserRepository.findByUserName(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;

    }

    public void saveNewUser(String username, String password) {
        ApplicationUserModel newUser = new ApplicationUserModel(username, passwordEncoder.encode(password),"ROLE_SURVEYCREATOR");
        applicationUserRepository.save(newUser);
    }

    //Get All available users as List<ApplicationUserModel>
    public List<ApplicationUserModel> getAllUsers(){
        return applicationUserRepository.findAll();
    }

    public void deleteUserByName(String userName) {
        applicationUserRepository.deleteByUserName(userName);
    }

    public ApplicationUserModel getUserByName(String userName){
        return applicationUserRepository.findByUserName(userName);
    }
}
