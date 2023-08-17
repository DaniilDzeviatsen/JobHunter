package com.example.jobhunter.model.security;

import lombok.Value;

import java.time.Instant;

@Value
public class AccessToken {

    String value;

    AccountPrincipal principal;

    Instant issuedAt;

    Instant expiresAt;
}
