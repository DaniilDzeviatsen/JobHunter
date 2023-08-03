package com.example.jobhunter.repository;


import com.example.jobhunter.model.candidate.Candidate;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepositoryImpl extends BaseRepositoryImpl<Candidate, Long>
implements CandidateRepository{

   public CandidateRepositoryImpl(){
       super(Candidate.class);
   }
}
