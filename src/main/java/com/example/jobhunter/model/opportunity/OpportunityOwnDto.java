package com.example.jobhunter.model.opportunity;

import lombok.Value;

import java.time.Instant;

@Value
public class OpportunityOwnDto {
    long id;

    String title;

    String description;

    boolean active;

    Instant createdAt;

    public static OpportunityOwnDto from(Opportunity opportunity){
        return new OpportunityOwnDto(
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getDescription(),
                opportunity.getActive(),
                opportunity.getCreatedAt()
        );
    }
}
