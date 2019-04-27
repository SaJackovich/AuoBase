package com.project.web.converter;

import com.project.db.constant.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringToUserRoleConverter implements Converter<String, UserRole> {

    private static final String INVALID_USER_ROLE = "Invalid user role: ";
    private static final String VALID_USER_ROLE = " . Should be: 'admin', 'dispatcher', 'driver'";

    @Override
    public UserRole convert(String userRole) {
        List<String> userRoles = Arrays.stream(UserRole.values())
                .map(UserRole::getPosition)
                .collect(Collectors.toList());
        if (userRole == null || !userRoles.contains(userRole)) {
            throw new IllegalArgumentException(INVALID_USER_ROLE + userRole + VALID_USER_ROLE);
        }
        return UserRole.valueOf(userRole.toUpperCase());
    }
}
