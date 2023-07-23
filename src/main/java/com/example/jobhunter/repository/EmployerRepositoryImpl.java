package com.example.jobhunter.repository;

import com.example.jobhunter.model.Employer;
import com.example.jobhunter.model.Vacancy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class EmployerRepositoryImpl implements EmployerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVacancy(Vacancy vacancy) {
        entityManager.persist(vacancy);

        log.info("Vacancy has been created: {}" + vacancy.getVacancyName());
    }

    @Override
    public void removeVacancy(Vacancy vacancy) {
        entityManager.remove(vacancy);

        log.info("Vacancy has been removed: {}" + vacancy.getVacancyName());
    }

    @Override
    public void createEmployer(Employer employer) {
        entityManager.persist(employer);

        log.info("Employer has been created: {}" + employer.getEmployerName());
    }

    @Override
    public void removeEmployer(Employer employer) {
        entityManager.remove(employer);

        log.info("Employer has been removed: {}" + employer.getEmployerName());
    }

    @Override
    public List<Vacancy> getAllVacanciesByEmployer(long employerId, int pageNum) {
        TypedQuery<Vacancy> query = entityManager.createQuery(
                        """
                                SELECT vacancy
                                FROM Vacancy vacancy
                                WHERE vacancy.employer =: employerId
                                """, Vacancy.class)
                .setMaxResults(10).setFirstResult((pageNum - 1) * 10);
        query.setParameter("employerId", employerId);
        return query.getResultList();
    }

    @Override
    public List<Vacancy> getActiveVacanciesByEmployer(long employerId, int pageNum) {
        TypedQuery<Vacancy> query = entityManager.createQuery("""
                        SELECT vacancy
                        FROM Vacancy vacancy
                        JOIN FETCH vacancy.employer
                        WHERE vacancy.isActive is TRUE
                         """, Vacancy.class)
                .setMaxResults(10)
                .setFirstResult((pageNum - 1) * 10);
        query.setParameter("employerId", employerId);
        return query.getResultList();


    }
}
