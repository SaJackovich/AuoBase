package com.project.web.advice.editor;

import com.project.core.service.UserService;
import com.project.db.entity.User;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class UserPropertyEditor extends PropertyEditorSupport {

    private UserService userService;

    public UserPropertyEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String id) {
        setValue(convertTextToUser(id));
    }

    private User convertTextToUser(String id) {
        return userService.getById(Long.valueOf(id));
    }
}
