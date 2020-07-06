package com.example.questions.security;

import com.example.questions.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO: disable csrf and make /register Page work
                .authorizeRequests()
                .antMatchers("/","/answerSurvey.html","/register").permitAll()
                .antMatchers("/questions.html").hasAnyAuthority("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/createSurvey.html").hasAnyAuthority("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/answerSurveyHome.html").hasAnyAuthority("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .antMatchers("/saveSurveyResults.html").hasAnyAuthority("ROLE_ADMIN", "ROLE_SURVEYCREATOR")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                //.loginPage()
                //.loginProcessingUrl()
               // .defaultSuccessUrl()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                //.logoutUrl("/")
                .deleteCookies();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    ApplicationUserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
