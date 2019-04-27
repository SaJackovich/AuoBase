package com.project.web.converter;

import com.project.core.exception.NullFormDtoException;
import com.project.db.constant.UserRole;
import com.project.db.entity.User;
import com.project.web.dto.RegistrationFormDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class RegistrationFormDtoToUserConverter implements Converter<RegistrationFormDto, User> {

    private static final String REGISTRATION_FORM_IS_NULL = "Registration form is null";

    @Override
    public User convert(RegistrationFormDto formDto) {
        if (Objects.isNull(formDto)) {
            throw new NullFormDtoException(REGISTRATION_FORM_IS_NULL);
        }
        return User.builder()
                .username(formDto.getUsername())
                .email(formDto.getEmail())
                .password(formDto.getPassword())
                .firstName(formDto.getFirstName())
                .lastName(formDto.getLastName())
                .roles(Collections.singletonList(UserRole.DRIVER))
                .verifiedPassword(formDto.getVerifiedPassword())
                .build();
    }
}
