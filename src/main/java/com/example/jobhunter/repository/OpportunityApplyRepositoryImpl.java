package com.example.jobhunter.repository;

import com.example.jobhunter.model.OpportunityApply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OpportunityApplyRepositoryImpl extends BaseRepositoryImpl<OpportunityApply, Long>
        implements OpportunityApplyRepository {

    public OpportunityApplyRepositoryImpl() {
        super(OpportunityApply.class);
    }

    @Override
    public List<OpportunityApply> findPageByOpportunityWithCandidate(long opportunityId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT apply
                        FROM OpportunityApply apply
                        JOIN FETCH apply.candidate
                        JOIN FETCH apply.candidate.cv
                        WHERE apply.opportunity.id=:opportunityId
                        ORDER BY apply.createdAt DESC
                        """, OpportunityApply.class)
                .setParameter("opportunityId", opportunityId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<OpportunityApply> findPageByEmployerWithOpportunityAndCandidate(long employerId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT apply
                        FROM OpportunityApply apply
                        JOIN FETCH apply.opportunity
                        JOIN FETCH apply.candidate
                        JOIN FETCH apply.candidate.cv
                        WHERE apply.opportunity.employer.id=:employerId
                        ORDER BY apply.createdAt DESC
                        """, OpportunityApply.class)
                .setParameter("employerId", employerId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }
}
