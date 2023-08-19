package com.example.jobhunter.controller;

import com.example.jobhunter.model.employer.EmployerPrincipal;
import com.example.jobhunter.model.opportunity.OpportunityOwnDto;
import com.example.jobhunter.service.OpportunityEmployerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employer-api")
@RequiredArgsConstructor
@Tag(name = "Employer API", description = "Endpoints that require employer authentication")
public class EmployerApiController {
    private final OpportunityEmployerService opportunityService;

    @GetMapping("/opportunities")
    public List<OpportunityOwnDto> getPageOpportunitiesOwn(
            @RequestParam int page,
            @AuthenticationPrincipal EmployerPrincipal principal
    ){
        return opportunityService.getPageOfOwn(page, principal);
    }
}
