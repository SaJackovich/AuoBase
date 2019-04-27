package com.project.web.controller;

import com.project.core.service.AutoService;
import com.project.core.service.RequestService;
import com.project.core.service.UserService;
import com.project.db.entity.Auto;
import com.project.db.entity.Request;
import com.project.web.controller.constant.ControllerConstant;
import com.project.web.dto.AutoSearchDto;
import com.project.web.dto.RequestFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    private ConversionService conversionService;

    private AutoService autoService;

    private UserService userService;

    @Autowired
    public RequestController(RequestService requestService, ConversionService conversionService, AutoService autoService, UserService userService) {
        this.requestService = requestService;
        this.conversionService = conversionService;
        this.autoService = autoService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('ADMIN')")
    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false) String searchQuery,
                         @PageableDefault(sort = {ControllerConstant.REQUEST_CREATED}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.PAGE, requestService.getBySearchQuery(searchQuery, pageable));

        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.REQUESTS);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('ADMIN')")
    @GetMapping("/info/{request}")
    public String info(Model model,
                       @PathVariable(ControllerConstant.REQUEST) Request request) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());

        model.addAttribute(ControllerConstant.REQUEST, request);
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.REQUEST_ARTICLE);
        model.addAttribute(ControllerConstant.WITH_AUTO, false);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('ADMIN')")
    @GetMapping("/info/{request}/{auto}")
    public String openRequestWithAuto(Model model,
                                      @PathVariable(ControllerConstant.REQUEST) Request request,
                                      @PathVariable(ControllerConstant.AUTO) Auto auto) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());

        model.addAttribute(ControllerConstant.AUTO, auto);
        model.addAttribute(ControllerConstant.REQUEST, request);
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.REQUEST_ARTICLE);
        model.addAttribute(ControllerConstant.WITH_AUTO, true);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('ADMIN')")
    @GetMapping("/{request}/auto")
    public String getFittingAutoForJourney(Model model,
                                           @PathVariable Request request,
                                           @RequestParam(value = ControllerConstant.MODEL_SEARCH, required = false) String autoModel,
                                           @PageableDefault(sort = {ControllerConstant.AUTO_MODEL}, direction = Sort.Direction.ASC) Pageable pageable) {
        AutoSearchDto searchDto = conversionService.convert(request, AutoSearchDto.class);
        searchDto.setModelSearch(autoModel);

        model.addAttribute(ControllerConstant.PAGE, autoService.getFittingAutoByRequest(searchDto, pageable));
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());

        model.addAttribute(ControllerConstant.AUTO_KIND_WORK, ControllerConstant.REQUEST_AUTO);
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.AUTO_LIST);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.REQUEST_INFO_OLD);
        return ControllerConstant.INFO_TEMPLATE;
    }

    @PostMapping("/add")
    public String add(RequestFormDto formDto,
                      RedirectAttributes attributes) {
        Request request = requestService.add(conversionService.convert(formDto, Request.class), userService.getCurrentUser());
        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.ADDED_REQUEST + request.getFrom()
                + " - " + request.getTo() + ControllerConstant.WAS_CREATED_SUCCESSFUL);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

}
