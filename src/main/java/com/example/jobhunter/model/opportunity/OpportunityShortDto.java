package com.example.jobhunter.model.opportunity;

import com.example.jobhunter.model.employer.EmployerShortDto;
import lombok.Value;

@Value
public class OpportunityShortDto {
    long id;

    EmployerShortDto employer;

    String title;

    public static OpportunityShortDto from (Opportunity opportunity){
        return new OpportunityShortDto(
                opportunity.getId(),
                EmployerShortDto.from(opportunity.getEmployer()),
                opportunity.getTitle()
        );
    }
}
