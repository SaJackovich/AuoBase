package com.project.web.dto;

import com.project.web.controller.constant.DtoMessage;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@ToString
@Data
public class AutoFormDto {

    @NotBlank(message = DtoMessage.MODEL_CANNOT_BE_NULL)
    private String model;

    @NotBlank(message = DtoMessage.CONDITION_CANNOT_BE_NULL)
    private String condition;

    @Min(value = 0)
    private double height;

    @Min(value = 0)
    private double width;

    @Min(value = 0)
    private int carrying;

}
