package com.example.jobhunter.model.apply;

import com.example.jobhunter.model.candidate.CandidateDto;
import lombok.Value;

import java.time.Instant;

@Value
public class OpportunityApplyShortDto {
    long id;
    CandidateDto candidate;
    String coveringLetter;
    Instant createdAt;

    public static OpportunityApplyShortDto from (OpportunityApply apply){
        return new OpportunityApplyShortDto(
                apply.getId(),
                CandidateDto.from(apply.getCandidate()),
                apply.getCoveringLetter(),
                apply.getCreatedAt()
        );
    }
}
