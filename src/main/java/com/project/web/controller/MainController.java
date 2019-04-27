package com.project.web.controller;

import com.project.web.controller.constant.ControllerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return ControllerConstant.REDIRECT + ControllerConstant.JOURNEY_URL;
    }

}
