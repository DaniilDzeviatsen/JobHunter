package com.example.jobhunter.repository;

import com.example.jobhunter.model.employer.Employer;

import java.util.Optional;

public interface EmployerRepository extends BaseRepository<Employer, Long> {

    Optional<Employer> findByEmail(String email);

}
