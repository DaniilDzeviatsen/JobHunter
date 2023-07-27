package com.example.jobhunter.repository;


import com.example.jobhunter.model.Candidate;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepositoryImpl extends BaseRepositoryImpl<Candidate, Long>
implements CandidateRepository{

   public CandidateRepositoryImpl(){
       super(Candidate.class);
   }
}
