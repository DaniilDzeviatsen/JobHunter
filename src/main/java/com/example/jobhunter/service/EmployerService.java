package com.example.jobhunter.service;

import com.example.jobhunter.model.employer.EmployerSignInDto;
import com.example.jobhunter.model.employer.EmployerSignUpDto;
import com.example.jobhunter.model.security.AccessToken;

public interface EmployerService {

    AccessToken signIn (EmployerSignInDto dto);

    AccessToken signUp (EmployerSignUpDto dto);

}
