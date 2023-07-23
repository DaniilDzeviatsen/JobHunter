package com.example.jobhunter.repository;


import com.example.jobhunter.model.Candidate;
import com.example.jobhunter.model.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@AllArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addCandidate(Candidate candidate) {
        entityManager.persist(candidate);

        log.info("Candidate has been created: {}" + candidate.getCandidateEmail());
    }

    @Override
    public void addResponse(Response response) {
        entityManager.persist(response);

        log.info("Response has been added:{}" + response.getCandidate());
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        entityManager.remove(candidate);

        log.info("Candidate has been removed:{}" + candidate.getCandidateEmail());
    }

    @Override
    public void removeResponse(Response response) {
        entityManager.remove(response);

        log.info("response has been removed: {}" + response.getCandidate());
    }

    @Override
    public List<Response> getResponsesByVacancy(long vacancyId, int pageNum) {
        TypedQuery<Response> query = entityManager.createQuery(
                        """
                                SELECT response
                                FROM Response response
                                JOIN FETCH response.candidate
                                WHERE response.vacancy =: vacancyId
                                """, Response.class)
                .setMaxResults(10)
                .setFirstResult((pageNum - 1) * 10);
        query.setParameter("vacancyId", vacancyId);
        return query.getResultList();
    }

    @Override
    public List<Response> getResponseByEmployer(long employerId, int pageNum) {
        TypedQuery<Response> query = entityManager.createQuery(
                        """
                                SELECT response
                                FROM Response response
                                JOIN FETCH response.candidate
                                JOIN FETCH response.vacancy
                                WHERE response.vacancy.employer =: employerId
                                """, Response.class)
                .setMaxResults(10)
                .setFirstResult((pageNum - 1) * 10);
        query.setParameter("employerId", employerId);
        return query.getResultList();
    }
}
