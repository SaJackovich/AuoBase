package com.project.web.advice;

import com.project.core.service.AutoService;
import com.project.core.service.JourneyService;
import com.project.core.service.RequestService;
import com.project.core.service.UserService;
import com.project.db.entity.Auto;
import com.project.db.entity.Journey;
import com.project.db.entity.Request;
import com.project.db.entity.User;
import com.project.web.advice.editor.AutoPropertyEditor;
import com.project.web.advice.editor.JourneyPropertyEditor;
import com.project.web.advice.editor.RequestPropertyEditor;
import com.project.web.advice.editor.UserPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class EditorsControllerAdvice {

    private UserService userService;
    private JourneyService journeyService;
    private RequestService requestService;
    private AutoService autoService;

    @Autowired
    public EditorsControllerAdvice(UserService userService, JourneyService journeyService, RequestService requestService, AutoService autoService) {
        this.userService = userService;
        this.journeyService = journeyService;
        this.requestService = requestService;
        this.autoService = autoService;
    }

    @InitBinder
    public void initBunderAll(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserPropertyEditor(userService));
        binder.registerCustomEditor(Journey.class, new JourneyPropertyEditor(journeyService));
        binder.registerCustomEditor(Request.class, new RequestPropertyEditor(requestService));
        binder.registerCustomEditor(Auto.class, new AutoPropertyEditor(autoService));
    }

}
