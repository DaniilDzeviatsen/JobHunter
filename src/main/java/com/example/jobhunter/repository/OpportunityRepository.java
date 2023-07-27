package com.example.jobhunter.repository;

import com.example.jobhunter.model.Opportunity;

import java.util.List;

public interface OpportunityRepository extends BaseRepository<Opportunity, Long> {
    List<Opportunity> findPageByEmployer(long employerId, int pageSize, int pageNumber);

    List<Opportunity> findPageByActiveAndTitleContainsWithEmployer(String titleQuery, int limit, int offset);
}
