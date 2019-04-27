package com.project.db.constant;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ADMIN("admin"),
    DISPATCHER("dispatcher"),
    DRIVER("driver");

    private String position;

    UserRole(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
