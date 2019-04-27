package com.project.web.dto;

import com.project.web.controller.constant.DtoMessage;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString
@Data
public class RegistrationFormDto {

    @NotBlank(message = DtoMessage.USERNAME_CANNOT_BE_NULL)
    private String username;

    @NotBlank(message = DtoMessage.FIRST_NAME_CANNOT_BE_NULL)
    private String firstName;

    @NotBlank(message = DtoMessage.LAST_NAME_CANNOT_BE_NULL)
    private String lastName;

    @NotBlank(message = DtoMessage.PASSWORD_CANNOT_BE_NULL)
    private String password;

    @NotBlank(message = DtoMessage.VERIFIED_PASSWORD_CANNOT_BE_NULL)
    private String verifiedPassword;

    @NotBlank(message = DtoMessage.EMAIL_CANNOT_BE_NULL)
    private String email;

}
