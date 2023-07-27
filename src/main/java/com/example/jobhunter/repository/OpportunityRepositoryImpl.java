package com.example.jobhunter.repository;

import com.example.jobhunter.model.Opportunity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpportunityRepositoryImpl extends BaseRepositoryImpl<Opportunity, Long>
        implements OpportunityRepository {

    public OpportunityRepositoryImpl() {
        super(Opportunity.class);
    }

    @Override
    public List<Opportunity> findPageByEmployer(long employerId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT opportunity
                        FROM Opportunity opportunity
                        WHERE opportunity.employer.id =:employerId
                        ORDER BY opportunity.createdAt DESC
                        """, Opportunity.class)
                .setParameter("employerId", employerId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<Opportunity> findPageByActiveAndTitleContainsWithEmployer(String titleQuery, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT opportunity
                        FROM Opportunity opportunity
                          JOIN FETCH opportunity.employer
                        WHERE opportunity.active
                          AND opportunity.title ILIKE :titleQuery
                        ORDER BY opportunity.createdAt DESC
                        """, Opportunity.class)
                .setParameter("titleQuery", titleQuery)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }
}
