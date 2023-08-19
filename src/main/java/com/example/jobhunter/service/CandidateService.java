package com.example.jobhunter.service;

import com.example.jobhunter.model.candidate.CandidateSignInDto;
import com.example.jobhunter.model.candidate.CandidateSignUpDto;
import com.example.jobhunter.model.security.AccessToken;

public interface CandidateService {

    AccessToken signIn(CandidateSignInDto dto);

    AccessToken signUp(CandidateSignUpDto dto);
}
