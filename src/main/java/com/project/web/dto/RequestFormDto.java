package com.project.web.dto;

import com.project.web.controller.constant.DtoMessage;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@ToString
@Data
public class RequestFormDto {

    @Min(value = 0)
    private double height;

    @Min(value = 0)
    private double width;

    @Min(value = 0)
    private int carrying;

    @NotBlank(message = DtoMessage.FROM_PLACE_CANNOT_BE_NULL)
    private String from;

    @NotBlank(message = DtoMessage.TO_PLACE_CANNOT_BE_NULL)
    private String to;

    private String message;

}
