package com.project.web.converter;

import com.project.core.exception.NullFormDtoException;
import com.project.db.entity.User;
import com.project.web.dto.UserFormDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserFormDtoToUserConverter implements Converter<UserFormDto, User> {

    private static final String USER_FORM_IS_NULL = "User form is null";

    @Override
    public User convert(UserFormDto formDto) {
        if (formDto == null) {
            throw new NullFormDtoException(USER_FORM_IS_NULL);
        }
        return User.builder()
                .firstName(formDto.getFirstName())
                .lastName(formDto.getLastName())
                .email(formDto.getEmail())
                .password(formDto.getPassword())
                .verifiedPassword(formDto.getVerifiedPassword())
                .username(formDto.getUsername())
                .roles(Collections.singletonList(formDto.getPosition()))
                .build();
    }
}
