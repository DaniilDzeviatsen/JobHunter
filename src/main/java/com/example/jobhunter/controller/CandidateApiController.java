package com.example.jobhunter.controller;

import com.example.jobhunter.model.apply.OpportunityApplyCreateDto;
import com.example.jobhunter.model.apply.OpportunityApplyShortOwnDto;
import com.example.jobhunter.model.candidate.CandidatePrincipal;
import com.example.jobhunter.service.OpportunityCandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate-api")
@RequiredArgsConstructor
@Tag(name="Candidate API", description="Endpoints that require candidate authentication")
public class CandidateApiController {

    private final OpportunityCandidateService opportunityService;

    @PostMapping("/opportunity-applies")
    public OpportunityApplyShortOwnDto sendOpportunityApply(
            @RequestBody OpportunityApplyCreateDto dto,
            @AuthenticationPrincipal CandidatePrincipal principal
            ){
        return opportunityService.sendApply(dto, principal);
    }
}
