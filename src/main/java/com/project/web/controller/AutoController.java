package com.project.web.controller;

import com.project.core.service.AutoService;
import com.project.core.service.LogoService;
import com.project.core.service.UserService;
import com.project.db.entity.Auto;
import com.project.web.controller.constant.ControllerConstant;
import com.project.web.dto.AutoFormDto;
import com.project.web.dto.AutoSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auto")
public class AutoController {

    private ConversionService converter;

    private AutoService autoService;

    private LogoService logoService;

    private UserService userService;

    @Autowired
    public AutoController(ConversionService converter, AutoService autoService, LogoService logoService, UserService userService) {
        this.converter = converter;
        this.autoService = autoService;
        this.logoService = logoService;
        this.userService = userService;
    }

    @GetMapping
    public String getAll(AutoSearchDto searchDto, Model model,
                         @PageableDefault(sort = {ControllerConstant.AUTO_MODEL}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute(ControllerConstant.PAGE, autoService.getFittingAutoByRequest(searchDto, pageable));

        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.AUTO_LIST);
        model.addAttribute(ControllerConstant.AUTO_KIND_WORK, ControllerConstant.DELETE_AUTO);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.ADD_AUTO);
        return ControllerConstant.EDIT_TEMPLATE;
    }

    @PostMapping("/add")
    public String add(AutoFormDto formDto, RedirectAttributes attributes,
                      @RequestParam(ControllerConstant.AUTO_LOGO) MultipartFile file) {
        String fileFullName = logoService.save(ControllerConstant.AUTO, userService.getCurrentUser().getUsername(), file);
        Auto auto = converter.convert(formDto, Auto.class);
        auto.setLogo(ControllerConstant.AUTO + "\\" + fileFullName);

        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.ADDED_AUTO + auto.getModel() + ControllerConstant.WAS_ADDED_SUCCESSFUL);
        autoService.add(auto);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

    @GetMapping("/delete/{auto}")
    public String openDeleteForm(Model model,
                                 @PathVariable(ControllerConstant.AUTO) Auto auto) {
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.DELETE_AUTO);
        model.addAttribute(ControllerConstant.AUTO, auto);
        return ControllerConstant.INFO_TEMPLATE;
    }

    //todo Redid this mapping to delete
    @PostMapping("/delete/{auto}")
    public String delete(@PathVariable(ControllerConstant.AUTO) Auto auto,
                         RedirectAttributes attributes) {
        autoService.delete(auto);

        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.ADDED_AUTO
                + auto.getModel() + ControllerConstant.WAS_ADDED_SUCCESSFUL);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

}
