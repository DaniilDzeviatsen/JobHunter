package com.example.jobhunter.service;

import com.example.jobhunter.model.security.AccessToken;
import com.example.jobhunter.model.security.AccountPrincipal;

public interface AccessTokenService {
    AccessToken generate (AccountPrincipal principal);

    AccountPrincipal authenticate(String accessTokenValue);
}
