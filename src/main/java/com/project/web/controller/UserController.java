package com.project.web.controller;

import com.project.core.service.UserService;
import com.project.db.entity.User;
import com.project.web.controller.constant.ControllerConstant;
import com.project.web.dto.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private ConversionService convert;

    @Autowired
    public UserController(UserService userService, ConversionService convert) {
        this.userService = userService;
        this.convert = convert;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.ADD_USER);
        return ControllerConstant.EDIT_TEMPLATE;
    }

    @PostMapping("/add")
    public String addUser(@Valid UserFormDto userForm, RedirectAttributes attributes) {
        User user = convert.convert(userForm, User.class);
        userService.add(user);
        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.USER_WAS_ADDED_WITHOUT_PROBLEMS);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

    @GetMapping("/delete")
    public String deleteForm(Model model) {
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.CHECK_FOR_USER);
        return ControllerConstant.EDIT_TEMPLATE;
    }

    //todo Redid to delete method
    @PostMapping("/delete")
    public String deleteUser(@RequestParam(ControllerConstant.ID) User user, RedirectAttributes attributes) {
        userService.delete(user);
        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.USER_WITH_USERNAME
                + user.getUsername() + ControllerConstant.WAS_DELETED);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

    @GetMapping("/info/{userInfo}")
    public String getUserInfo(@PathVariable(ControllerConstant.USER_INFO) User userInfo, Model model) {
        model.addAttribute(ControllerConstant.USER_INFO, userInfo);
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.USER_INFO_TEMPLATE);
        return ControllerConstant.INFO_TEMPLATE;
    }

    @GetMapping("/info")
    public String getUserByEmailOrUsername(String emailOrUsername) {
        User user = userService.getUserByEmailOrUsername(emailOrUsername);
        return ControllerConstant.REDIRECT + ControllerConstant.USER_INFO_URL + user.getId();
    }

}
