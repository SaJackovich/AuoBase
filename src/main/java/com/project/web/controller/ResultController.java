package com.project.web.controller;

import com.project.web.controller.constant.ControllerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String resultForm() {
        return ControllerConstant.RESULT_TEMPLATE;
    }

}
