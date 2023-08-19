package com.example.jobhunter.service;

import com.example.jobhunter.model.apply.OpportunityApplyDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortDto;
import com.example.jobhunter.model.employer.EmployerPrincipal;
import com.example.jobhunter.model.opportunity.OpportunityOwnDto;
import com.example.jobhunter.model.opportunity.OpportunityUpdateDto;

import java.util.List;

public interface OpportunityEmployerService {

    List<OpportunityOwnDto> getPageOfOwn(int pageNumber, EmployerPrincipal principal);

    OpportunityOwnDto getOwnById(long id, EmployerPrincipal principal);

    List<OpportunityApplyShortDto> getAppliesPageByOpportunity(long opportunityId, int pageNumber, EmployerPrincipal principal);

    List<OpportunityApplyDto> getAppliesPageByOwnOpportunities(int pageNumber, EmployerPrincipal principal);

    OpportunityOwnDto create(OpportunityUpdateDto dto, EmployerPrincipal principal);

    OpportunityOwnDto update(long id, OpportunityUpdateDto dto, EmployerPrincipal principal);
}
