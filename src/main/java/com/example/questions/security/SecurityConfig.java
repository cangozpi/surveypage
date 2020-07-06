package com.example.questions.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/answerSurvey.htm").permitAll()
                .antMatchers("/questions.html").hasAnyRole("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/createSurvey.html").hasAnyRole("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/answerSurveyHome.html").hasAnyRole("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/saveSurveyResults.html").hasAnyRole("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .and()
                .formLogin();


    }
}
