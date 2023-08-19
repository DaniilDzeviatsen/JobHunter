package com.example.jobhunter.config;

import com.example.jobhunter.model.security.AccountPrincipal;
import com.example.jobhunter.service.AccessTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.List;

@RequiredArgsConstructor
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer";

    private final AccessTokenService accessTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain  chain) throws ServletException, IOException {
        String authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            String accessTokenValue = authorizationHeader.substring(TOKEN_PREFIX.length());
            AccountPrincipal principal = accessTokenService.authenticate(accessTokenValue);
            List<GrantedAuthority> authorities = List.of(principal.getRole());
            authenticate(principal, authorities);
        }
        chain.doFilter(req, resp);
    }

    private void authenticate(Object principal, List<GrantedAuthority> authorities) {
        Authentication auth = new TestingAuthenticationToken(principal, null, authorities);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }
}
