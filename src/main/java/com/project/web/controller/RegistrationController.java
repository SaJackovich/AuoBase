package com.project.web.controller;

import com.project.core.service.UserService;
import com.project.db.entity.User;
import com.project.web.controller.constant.ControllerConstant;
import com.project.web.dto.RegistrationFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private ConversionService conversionService;

    private UserService userService;

    @Autowired
    public RegistrationController(ConversionService conversionService, UserService userService) {
        this.conversionService = conversionService;
        this.userService = userService;
    }

    @GetMapping
    public String loginForm() {
        return ControllerConstant.REGISTRATION;
    }

    @PostMapping
    public String addUser(RegistrationFormDto formDto, Model model) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        User user = conversionService.convert(formDto, User.class);
        userService.add(user);
        return ControllerConstant.REDIRECT + ControllerConstant.JOURNEY_URL;
    }

}
