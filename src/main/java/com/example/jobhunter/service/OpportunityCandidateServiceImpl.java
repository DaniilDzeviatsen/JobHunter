package com.example.jobhunter.service;

import com.example.jobhunter.exception.BusinessException;
import com.example.jobhunter.model.apply.OpportunityApply;
import com.example.jobhunter.model.apply.OpportunityApplyCreateDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortOwnDto;
import com.example.jobhunter.model.candidate.Candidate;
import com.example.jobhunter.model.candidate.CandidatePrincipal;
import com.example.jobhunter.model.opportunity.Opportunity;
import com.example.jobhunter.model.opportunity.OpportunityDto;
import com.example.jobhunter.model.opportunity.OpportunityShortDto;
import com.example.jobhunter.repository.CandidateRepository;
import com.example.jobhunter.repository.OpportunityApplyRepository;
import com.example.jobhunter.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityCandidateServiceImpl implements OpportunityCandidateService{

    public static final int OPPORTUNITIES_PAGE_SIZE =10;

    private final OpportunityRepository opportunityRepo;

    private final OpportunityApplyRepository applyRepo;

    private final CandidateRepository candidateRepo;


    @Override
    @Transactional(readOnly = true)
    public List<OpportunityShortDto> getPageByTitleQuery(String titleQuery, int pageNumber) {
        String dbTitleQuery = "%"+titleQuery+"%";
        return opportunityRepo.findPageByActiveAndTitleContainsWithEmployer(dbTitleQuery, OPPORTUNITIES_PAGE_SIZE, pageNumber)
                .stream()
                .map(OpportunityShortDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OpportunityDto getById(long id, CandidatePrincipal principal) {
        Opportunity opportunity=opportunityRepo.findByIdWithEmployer(id)
                .orElseThrow(()-> new BusinessException("Opportunity is not found"));
        OpportunityApply ownApply = principal == null
                ? null
                : applyRepo.findByOpportunityAndCandidate(id, principal.getId()).orElse(null);
        return OpportunityDto.from(opportunity, ownApply );
    }

    @Override
    @Transactional
    public OpportunityApplyShortOwnDto sendApply(OpportunityApplyCreateDto dto, CandidatePrincipal principal) {
        Opportunity opportunity=opportunityRepo.findById(dto.getOpportunityId())
                .orElseThrow(()->new BusinessException("Opportunity is not found"));

        boolean alreadyApplied = applyRepo.findByOpportunityAndCandidate(dto.getOpportunityId(),principal.getId())
                .isPresent();
        if(alreadyApplied){
            throw new BusinessException("Candidate is already applied");
        }
        Candidate candidate=candidateRepo.getReferenceById(principal.getId());
        Instant createdAt=Instant.now();
        OpportunityApply apply=new OpportunityApply()
                .setCandidate(candidate)
                .setOpportunity(opportunity)
                .setCoveringLetter(dto.getCoveringLetter())
                .setCreatedAt(createdAt);
        applyRepo.create(apply);
        return OpportunityApplyShortOwnDto.from(apply);
    }
}
