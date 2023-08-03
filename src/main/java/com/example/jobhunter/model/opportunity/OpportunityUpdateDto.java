package com.example.jobhunter.model.opportunity;

import lombok.Value;

@Value
public class OpportunityUpdateDto {
    String title;

    String description;

    boolean active;
}
