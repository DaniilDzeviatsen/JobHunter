package com.example.jobhunter.repository;

import com.example.jobhunter.model.candidate.Candidate;

import java.util.Optional;

public interface CandidateRepository extends BaseRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}
