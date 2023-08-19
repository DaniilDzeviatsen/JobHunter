package com.example.jobhunter.model.candidate;

import lombok.Value;

@Value
public class CandidateSignUpDto {
String email;

String password;

String firstName;

String lastName;

CvUpdateDto cv;

}
