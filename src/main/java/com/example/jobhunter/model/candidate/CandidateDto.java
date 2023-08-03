package com.example.jobhunter.model.candidate;

import lombok.Value;

@Value
public class CandidateDto {
    String email;
    String firstName;
    String lastName;
    CvDto cv;

    public static CandidateDto from(Candidate candidate){
        return new CandidateDto(
                candidate.getEmail(),
                candidate.getFirstName(),
                candidate.getLastName(),
                CvDto.from(candidate.getCv())
        );
    }
}
