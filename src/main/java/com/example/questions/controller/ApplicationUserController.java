package com.example.questions.controller;

import com.example.questions.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ApplicationUserController {

    @Autowired
    ApplicationUserService applicationUserService;

    @PostMapping("/register")
    public RedirectView registerNewUser(@RequestParam String username, @RequestParam String password){
        applicationUserService.saveNewUser(username, password);
        return new RedirectView("/");
    }

    @PostMapping("/deleteUser")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void deleteUserByName(@RequestParam String username){
        applicationUserService.deleteUserByName(username);
    }


}
