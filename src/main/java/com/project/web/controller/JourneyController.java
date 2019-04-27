package com.project.web.controller;

import com.project.core.service.JourneyService;
import com.project.core.service.UserService;
import com.project.db.constant.JourneyStatus;
import com.project.db.entity.Auto;
import com.project.db.entity.Journey;
import com.project.db.entity.Request;
import com.project.db.entity.User;
import com.project.web.controller.constant.ControllerConstant;
import com.project.web.dto.JourneySearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Validated
@RequestMapping("/journey")
public class JourneyController {

    private JourneyService journeyService;

    private UserService userService;

    @Autowired
    public JourneyController(JourneyService journeyService, UserService userService) {
        this.journeyService = journeyService;
        this.userService = userService;
    }

    @GetMapping()
    public String mainPage(@Valid JourneySearchDto parameterDto, Model model,
                           @PageableDefault(sort = {ControllerConstant.JOURNEY_CREATED}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute(ControllerConstant.PAGE, journeyService.getBySearchParams(parameterDto, pageable));
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.JOURNEY_LIST);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @PreAuthorize("hasAuthority('DRIVER')")
    @GetMapping("/my")
    public String privateJourney(Model model,
                                 @Valid JourneySearchDto parameterDto,
                                 @PageableDefault(sort = {ControllerConstant.JOURNEY_CREATED}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute(ControllerConstant.PAGE, journeyService.getBySearchParamsAndDriver(parameterDto,
                userService.getCurrentUser(), pageable));
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.JOURNEY_LIST);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @GetMapping("/info/{journey}")
    public String journeyInfo(@PathVariable(ControllerConstant.JOURNEY) Journey journey, Model model) {
        model.addAttribute(ControllerConstant.USER, userService.getCurrentUser());
        model.addAttribute(ControllerConstant.JOURNEY, journey);
        model.addAttribute(ControllerConstant.TEMPLATE, ControllerConstant.JOURNEY_ARTICLE);
        return ControllerConstant.MAIN_TEMPLATE;
    }

    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('ADMIN')")
    @PostMapping("/{request}/{auto}")
    public String addJourney(@PathVariable(ControllerConstant.REQUEST) Request request,
                             @PathVariable(ControllerConstant.AUTO) Auto auto,
                             RedirectAttributes attributes) {
        Journey journey = journeyService.add(request, auto);
        request.setProcessed(false);

        attributes.addFlashAttribute(ControllerConstant.RESULT, ControllerConstant.ADDED_JOURNEY + journey.getFrom()
                + " - " + journey.getTo() + ControllerConstant.WAS_CREATED_SUCCESSFUL);
        return ControllerConstant.REDIRECT + ControllerConstant.RESULT_URL;
    }

    @PostMapping("/edit/{journey}/{driver}")
    public String changeJourneyStatus(@PathVariable(ControllerConstant.JOURNEY) Journey journey,
                                      @PathVariable(ControllerConstant.DRIVER) User user,
                                      @RequestParam(ControllerConstant.STATUS) JourneyStatus status,
                                      @RequestParam(value = ControllerConstant.CONDITION, required = false) Boolean autoCondition) {
        journeyService.changeJourneyStatus(journey, user, status, autoCondition);
        return ControllerConstant.REDIRECT + ControllerConstant.JOURNEY_INFO_URL + journey.getId();
    }
}
