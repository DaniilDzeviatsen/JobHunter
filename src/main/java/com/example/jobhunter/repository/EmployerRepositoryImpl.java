package com.example.jobhunter.repository;

import com.example.jobhunter.model.Employer;
import org.springframework.stereotype.Repository;


@Repository
public class EmployerRepositoryImpl extends BaseRepositoryImpl<Employer, Long>
implements EmployerRepository{

    public EmployerRepositoryImpl(){
        super(Employer.class);
    }

}
