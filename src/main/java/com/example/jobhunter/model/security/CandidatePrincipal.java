package com.example.jobhunter.model.security;

import com.example.jobhunter.model.candidate.Candidate;
import lombok.Value;

@Value
public class CandidatePrincipal implements AccountPrincipal{
    long id;

    @Override
    public AccountRole getRole(){
        return AccountRole.CANDIDATE;
    }

    public static CandidatePrincipal from(Candidate candidate){
        return new CandidatePrincipal(candidate.getId());
    }
}
