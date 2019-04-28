package com.project.web.controller;

import com.project.core.exception.BDOperationException;
import com.project.core.exception.NullFormDtoException;
import com.project.core.exception.UserParametersException;
import com.project.web.controller.constant.ControllerConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class DefaultExceptionHandler {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Number format exception handler
     *
     * @param ex NumberFormatException
     * @return 400, Bad Request
     */
    @ExceptionHandler({
            NumberFormatException.class,
            NullFormDtoException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String format(Model model,
                        Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .lastNumber("4")
                                                                .error(ControllerConstant.BAD_REQUEST)
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }

    /**
     * Input user parameters exception handler
     *
     * @param ex UserParametersException
     * @return 409, Conflict
     */
    @ExceptionHandler(UserParametersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflict(Model model,
                           Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .lastNumber("9")
                                                                .error(ControllerConstant.CONFLICT)
                                                                .errorMessage(ex.getMessage())
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }

    /**
     * Invalid input exception handler
     *
     * @param ex ConstraintViolationException
     * @return 422, Bad Request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String invalidInput(Model model,
                               Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .secondNumber("2")
                                                                .lastNumber("4")
                                                                .error(ControllerConstant.INVALID_INPUT)
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }


    /**
     * Access denied exception handler
     *
     * @param ex AccessDeniedException
     * @return 403, Access Denied
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String access(Model model,
                         Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .lastNumber("3")
                                                                .error(ControllerConstant.ACCESS_DENIED)
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }

    /**
     * Precondition failed exception handler
     *
     * @param ex BDOperationException
     * @return 412, Precondition Failed
     */
    @ExceptionHandler(BDOperationException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public String preCondition(Model model,
                               Exception ex){
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .secondNumber("1")
                                                                .lastNumber("2")
                                                                .error(ControllerConstant.PRECONDITION_FAILED)
                                                                .errorMessage(ex.getMessage())
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }

    /**
     * Resource not found exception handler
     *
     * @param ex ResourceNotFoundException
     * @return 404, "Not Found"
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound(Model model,
                               Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute(ControllerConstant.CONTAINER, ExceptionContainer.builder()
                                                                .firstNumber("4")
                                                                .lastNumber("4")
                                                                .error(ControllerConstant.PAGE_NOT_FOUND)
                                                                .build());
        return ControllerConstant.ERROR_PAGE;
    }

    @AllArgsConstructor
    @Builder
    @Data
    private static class ExceptionContainer {
        private String firstNumber;
        private String secondNumber;
        private String lastNumber;
        private String error;
        private String errorMessage;
    }
}
