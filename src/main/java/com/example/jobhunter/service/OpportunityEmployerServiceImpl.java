package com.example.jobhunter.service;

import com.example.jobhunter.exception.BusinessException;
import com.example.jobhunter.model.apply.OpportunityApplyDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortDto;
import com.example.jobhunter.model.employer.Employer;
import com.example.jobhunter.model.employer.EmployerPrincipal;
import com.example.jobhunter.model.opportunity.Opportunity;
import com.example.jobhunter.model.opportunity.OpportunityOwnDto;
import com.example.jobhunter.model.opportunity.OpportunityUpdateDto;
import com.example.jobhunter.repository.EmployerRepository;
import com.example.jobhunter.repository.OpportunityApplyRepository;
import com.example.jobhunter.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityEmployerServiceImpl implements OpportunityEmployerService {

    public static final int OPPORTUNITIES_PAGE_SIZE = 10;

    public static final int APPLIES_PAGE_SIZE = 5;

    private final OpportunityRepository opportunityRepo;

    private final OpportunityApplyRepository applyRepo;

    private final EmployerRepository employerRepo;

    @Override
    @Transactional(readOnly = true)
    public List<OpportunityOwnDto> getPageOfOwn(EmployerPrincipal principal, int pageNumber) {
        return opportunityRepo.findPageByEmployer(principal.getId(), OPPORTUNITIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(OpportunityOwnDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OpportunityOwnDto getOwnById(long id, EmployerPrincipal principal) {
        Opportunity opportunity = opportunityRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Opportunity is nit found"));
        validateAccess(opportunity, principal);
        return OpportunityOwnDto.from(opportunity);
    }

    private void validateAccess(Opportunity opportunity, EmployerPrincipal principal) {
        long authenticatedId = principal.getId();
        long ownerId = opportunity.getEmployer().getId();
        if (authenticatedId != ownerId) {
            throw new BusinessException("Access denied");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpportunityApplyShortDto> getAppliesPageByOpportunity(long opportunityId, int pageNumber, EmployerPrincipal principal) {
        Opportunity opportunity = opportunityRepo.findById(opportunityId)
                .orElseThrow(() -> new BusinessException("Opportunity is not found"));
        validateAccess(opportunity, principal);
        return applyRepo.findPageByEmployerWithOpportunityAndCandidate(opportunityId, APPLIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(OpportunityApplyShortDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpportunityApplyDto> getAppliesPageByOwnOpportunities(int pageNumber, EmployerPrincipal principal) {
        return applyRepo.findPageByEmployerWithOpportunityAndCandidate(principal.getId(), APPLIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(OpportunityApplyDto::from)
                .toList();
    }

    @Override
    @Transactional
    public OpportunityOwnDto create(OpportunityUpdateDto dto, EmployerPrincipal principal) {
        Instant createdAt =Instant.now();
        Employer employer=employerRepo.getReferenceById(principal.getId());

        Opportunity opportunity=new Opportunity()
                .setEmployer(employer)
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setCreatedAt(createdAt)
                .setActive(dto.isActive());
        opportunityRepo.create(opportunity);
        return OpportunityOwnDto.from(opportunity);
    }

    @Override
    @Transactional
    public OpportunityOwnDto update(long id, OpportunityUpdateDto dto, EmployerPrincipal principal) {
        Opportunity opportunity=opportunityRepo.findById(id)
                .orElseThrow(()->new BusinessException("Opportunity is not found"));
        validateAccess(opportunity,principal);
        opportunity.setTitle(dto.getTitle()).getTitle();
        opportunity.setDescription(dto.getDescription());
        opportunity.setActive(dto.isActive());
        return OpportunityOwnDto.from(opportunity);
    }
}
