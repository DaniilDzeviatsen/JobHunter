package com.example.jobhunter.model.opportunity;

import com.example.jobhunter.model.apply.OpportunityApply;
import com.example.jobhunter.model.apply.OpportunityApplyShortDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortOwnDto;
import com.example.jobhunter.model.employer.EmployerShortDto;
import jakarta.annotation.Nullable;
import lombok.Value;

import java.util.Optional;

@Value
public class OpportunityDto {
    long id;
    EmployerShortDto employer;
    String title;
    String description;
    @Nullable
    OpportunityApplyShortOwnDto ownApply;

    public Optional<OpportunityApplyShortOwnDto> getOwnApply(){
        return Optional.ofNullable(ownApply);
    }

    public static OpportunityDto from (Opportunity opportunity, @Nullable OpportunityApply ownApply){
        return new OpportunityDto(
                opportunity.getId(),
                EmployerShortDto.from (opportunity.getEmployer()),
                opportunity.getTitle(),
                opportunity.getDescription(),
                ownApply == null? null:OpportunityApplyShortOwnDto.from(ownApply)
        );
    }
}
