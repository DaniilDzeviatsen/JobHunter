package com.example.jobhunter.model.security;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {

    CANDIDATE,

    EMPLOYER;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
