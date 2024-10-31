package com.ttnlr.planet.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {

    EMPLOYEE, // Роль продавца
    CUSTOMER,  // Роль покупателя
    FULL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
