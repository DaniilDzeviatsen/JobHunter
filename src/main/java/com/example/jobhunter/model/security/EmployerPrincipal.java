package com.example.jobhunter.model.security;

import com.example.jobhunter.model.employer.Employer;
import lombok.Value;

@Value
public class EmployerPrincipal implements AccountPrincipal{
    long id;

    @Override
    public AccountRole getRole(){
        return AccountRole.EMPLOYER;
    }
    public static EmployerPrincipal from(Employer employer){
        return new EmployerPrincipal(employer.getId());
    }
}
