package com.example.questions.service;

import com.example.questions.dao.ApplicationUserRepository;
import com.example.questions.model.ApplicationUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ApplicationUserModel user = applicationUserRepository.findByUserName(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getUserName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

    }
}
