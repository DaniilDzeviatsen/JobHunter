package com.example.jobhunter.service;

import com.example.jobhunter.model.apply.OpportunityApplyCreateDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortOwnDto;
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
        Opportunity opportunity=opportunityRepo.f
        return null;
    }

    @Override
    public OpportunityApplyShortOwnDto sendApply(OpportunityApplyCreateDto dto, CandidatePrincipal principal) {
        return null;
    }
}
