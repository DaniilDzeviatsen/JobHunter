package com.example.jobhunter.repository;

import com.example.jobhunter.model.apply.OpportunityApply;

import java.util.List;
import java.util.Optional;

public interface OpportunityApplyRepository extends BaseRepository<OpportunityApply, Long> {
    List <OpportunityApply> findPageByOpportunityWithCandidate(long opportunityId, int pageSize, int pageNumber);

    Optional<OpportunityApply> findByOpportunityAndCandidate(long opportunityId, long candidateId);
    List<OpportunityApply> findPageByEmployerWithOpportunityAndCandidate (long employerId, int pageSize, int pageNumber);
}
