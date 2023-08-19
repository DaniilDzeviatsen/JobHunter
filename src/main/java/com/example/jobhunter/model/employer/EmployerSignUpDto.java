package com.example.jobhunter.model.employer;

import lombok.Value;

import java.net.URI;

@Value
public class EmployerSignUpDto {
    String email;

    String password;

    String name;

    URI siteUrl;
}
