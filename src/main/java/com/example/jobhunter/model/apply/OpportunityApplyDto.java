package com.example.jobhunter.model.apply;

import com.example.jobhunter.model.candidate.CandidateDto;
import com.example.jobhunter.model.opportunity.OpportunityOwnDto;
import lombok.Value;

import java.time.Instant;

@Value
public class OpportunityApplyDto {
    long id;
    OpportunityOwnDto opportunity;
    CandidateDto candidate;
    String coveringLetter;
    Instant createdAt;

    public static OpportunityApplyDto from(OpportunityApply apply){
        return new OpportunityApplyDto(
                apply.getId(),
                OpportunityOwnDto.from(apply.getOpportunity()),
                CandidateDto.from(apply.getCandidate()),
                apply.getCoveringLetter(),
                apply.getCreatedAt()
        );
    }
}
