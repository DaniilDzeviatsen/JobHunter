package com.example.jobhunter.service;

public interface EmployerService {

    AccessToken signIn (EmployerSignInDto dto);

    AccessToken signUp (EmployerSignUpDto dto);

}
