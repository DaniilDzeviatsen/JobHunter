package com.example.jobhunter.repository;

import com.example.jobhunter.model.Candidate;
import com.example.jobhunter.model.Response;

import java.util.List;

public interface CandidateRepository {

    void addCandidate (Candidate candidate);

    void addResponse(Response response);

    void removeCandidate(Candidate candidate);

    void removeResponse(Response response);

    List<Response> getResponsesByVacancy (long vacancyId, int PageNum);

    List <Response> getResponseByEmployer (long employerId, int PageNum);

}
