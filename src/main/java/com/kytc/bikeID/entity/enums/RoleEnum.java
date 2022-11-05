package com.kytc.bikeID.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ANONYMOUS,
    USER,
    ADMIN;

    RoleEnum() {
    }

    @Override
    public String getAuthority() {

        return this.name();
    }
}
