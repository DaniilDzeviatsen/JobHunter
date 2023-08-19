package com.example.jobhunter.controller;

import com.example.jobhunter.model.candidate.CandidatePrincipal;
import com.example.jobhunter.model.candidate.CandidateSignInDto;
import com.example.jobhunter.model.candidate.CandidateSignUpDto;
import com.example.jobhunter.model.employer.EmployerSignInDto;
import com.example.jobhunter.model.employer.EmployerSignUpDto;
import com.example.jobhunter.model.opportunity.OpportunityDto;
import com.example.jobhunter.model.opportunity.OpportunityShortDto;
import com.example.jobhunter.model.security.AccessToken;
import com.example.jobhunter.service.CandidateService;
import com.example.jobhunter.service.EmployerService;
import com.example.jobhunter.service.OpportunityCandidateService;
import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public-api")
@RequiredArgsConstructor
@Tag(name = "Public API", description = "Endpoints that can be accessed without authentication")
public class PublicApiController {
    private final CandidateService candidateService;

    private final OpportunityCandidateService opportunityService;

    private final EmployerService employerService;

    @PostMapping("/candidates")
    public AccessToken signUpCandidate(@RequestBody CandidateSignUpDto dto){
        return candidateService.signUp(dto);
    }

    @PostMapping("/candidate/access-tokens")
    public AccessToken signInCandidate(@RequestBody CandidateSignInDto dto){
        return candidateService.signIn(dto);
    }

    @PostMapping("/employers")
    public AccessToken signUpEmployer(@RequestBody EmployerSignUpDto dto){
        return employerService.signUp(dto);
    }
    @PostMapping("/employers/access-tokens")
    public AccessToken signInEmployer(@RequestBody EmployerSignInDto dto){
        return employerService.signIn(dto);
    }
    @GetMapping("/opportunities")
    public List<OpportunityShortDto> getPageOfOpportunitiesByTitleQuery(
            @RequestParam String query,
            @RequestParam int page
    ){
        return opportunityService.getPageByTitleQuery(query, page);
    }
    @GetMapping("/opportunities/{opportunityId}")
    public OpportunityDto getOpportunityById(
            @PathVariable long opportunityId,
            @AuthenticationPrincipal @Nullable CandidatePrincipal candidatePrincipal
            ){
        return opportunityService.getById(opportunityId, candidatePrincipal);
    }
}
