package com.example.jobhunter.repository;

import com.example.jobhunter.model.Employer;
import com.example.jobhunter.model.Vacancy;

import java.util.List;

public interface EmployerRepository {

    void createEmployer(Employer employer);

    void removeEmployer(Employer employer);

    void createVacancy(Vacancy vacancy);

    void removeVacancy(Vacancy vacancy);

    List<Vacancy> getAllVacanciesByEmployer(long employerId, int pageNum);

    List<Vacancy> getActiveVacanciesByEmployer(long employerId, int pageNum);

}
