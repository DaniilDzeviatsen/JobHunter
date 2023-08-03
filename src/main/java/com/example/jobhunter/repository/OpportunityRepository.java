package com.example.jobhunter.repository;

import com.example.jobhunter.model.opportunity.Opportunity;

import java.util.List;
import java.util.Optional;

public interface OpportunityRepository extends BaseRepository<Opportunity, Long> {
    List<Opportunity> findPageByEmployer(long employerId, int pageSize, int pageNumber);

    Optional<Opportunity> findByIdWithEmployer(long opportunityId);

    List<Opportunity> findPageByActiveAndTitleContainsWithEmployer(String titleQuery, int limit, int offset);
}
