package com.example.jobhunter.model.employer;

import lombok.Value;

import java.net.URI;

@Value
public class EmployerShortDto {
    long id;
    String name;
    URI siteUrl;

    public static EmployerShortDto from(Employer employer){
        return new EmployerShortDto(
                employer.getId(),
                employer.getName(),
                employer.getSiteUrl()
        );
    }
}
