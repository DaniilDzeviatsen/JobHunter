package com.example.jobhunter.service;

import com.example.jobhunter.exception.BusinessException;
import com.example.jobhunter.model.employer.Employer;
import com.example.jobhunter.model.employer.EmployerSignInDto;
import com.example.jobhunter.model.employer.EmployerSignUpDto;
import com.example.jobhunter.model.security.AccessToken;
import com.example.jobhunter.model.security.EmployerPrincipal;
import com.example.jobhunter.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepo;

    private final AccessTokenService accessTokenService;

    private final PasswordEncoder passwordEncoder;

    private final TransactionOperations txOps;

    @Override
    public AccessToken signIn(EmployerSignInDto dto) {
        Employer employer = employerRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException(""));
        if (!passwordEncoder.matches(dto.getPassword(), employer.getPasswordHash())) {
            throw new BadCredentialsException("");
        }
        EmployerPrincipal principal = EmployerPrincipal.from(employer);
        return accessTokenService.generate(principal);
    }

    @Override
    public AccessToken signUp(EmployerSignUpDto dto) {
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        Employer employer=this.create(dto, passwordHash);

        EmployerPrincipal principal=EmployerPrincipal.from(employer);
        return accessTokenService.generate(principal);
    }

    private Employer create(EmployerSignUpDto dto, String passwordHash){
        return txOps.execute(tx->{
            boolean existByEmail = employerRepo.findByEmail(dto.getEmail()).isPresent();
            if (existByEmail){
                throw new BusinessException("");
            }
            Employer employer = new Employer()
                    .setEmail(dto.getEmail())
                    .setPasswordHash(passwordHash)
                    .setName(dto.getName())
                    .setSiteUrl(dto.getSiteUrl());
            employerRepo.create(employer);
            return employer;
        });
    }
}
