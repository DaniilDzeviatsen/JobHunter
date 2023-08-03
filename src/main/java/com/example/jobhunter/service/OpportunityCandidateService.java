package com.example.jobhunter.service;

import com.example.jobhunter.model.apply.OpportunityApplyCreateDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortOwnDto;
import com.example.jobhunter.model.candidate.CandidatePrincipal;
import com.example.jobhunter.model.opportunity.OpportunityDto;
import com.example.jobhunter.model.opportunity.OpportunityShortDto;
import jakarta.annotation.Nullable;

import java.util.List;

public interface OpportunityCandidateService {
    List<OpportunityShortDto> getPageByTitleQuery(String titleQuery, int pageNumber);

    OpportunityDto getById(long id, @Nullable CandidatePrincipal principal);

    OpportunityApplyShortOwnDto sendApply(OpportunityApplyCreateDto dto, CandidatePrincipal principal);
}
