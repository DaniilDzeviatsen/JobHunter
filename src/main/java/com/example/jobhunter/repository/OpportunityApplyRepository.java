package com.example.jobhunter.repository;

import com.example.jobhunter.model.OpportunityApply;

import java.util.List;

public interface OpportunityApplyRepository extends BaseRepository<OpportunityApply, Long> {
    List <OpportunityApply> findPageByOpportunityWithCandidate(long opportunityId, int pageSize, int pageNumber);

    List<OpportunityApply> findPageByEmployerWithOpportunityAndCandidate (long employerId, int pageSize, int pageNumber);
}
