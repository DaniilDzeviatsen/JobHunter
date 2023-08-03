package com.example.jobhunter.model.apply;

import lombok.Value;

import java.time.Instant;

@Value
public class OpportunityApplyShortOwnDto {
    long id;
    String coveringLetter;
    Instant createdAt;

    public static OpportunityApplyShortOwnDto from (OpportunityApply apply){
        return new OpportunityApplyShortOwnDto(
                apply.getId(),
                apply.getCoveringLetter(),
                apply.getCreatedAt()
        );
    }
}
