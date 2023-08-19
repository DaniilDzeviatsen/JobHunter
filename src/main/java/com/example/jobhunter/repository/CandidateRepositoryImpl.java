package com.example.jobhunter.repository;


import com.example.jobhunter.model.candidate.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CandidateRepositoryImpl extends BaseRepositoryImpl<Candidate, Long>
        implements CandidateRepository {

    public CandidateRepositoryImpl() {
        super(Candidate.class);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return entityManager.createQuery("""
                        SELECT candidate
                        FROM Candidate candidate
                        WHERE lower(candidate.email)=lower(:email) 
                        """, Candidate.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
