package com.example.jobhunter.service;

import com.example.jobhunter.exception.BusinessException;
import com.example.jobhunter.model.candidate.Candidate;
import com.example.jobhunter.model.candidate.CandidateSignInDto;
import com.example.jobhunter.model.candidate.CandidateSignUpDto;
import com.example.jobhunter.model.candidate.Cv;
import com.example.jobhunter.model.security.AccessToken;
import com.example.jobhunter.model.security.CandidatePrincipal;
import com.example.jobhunter.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepo;

    private final AccessTokenService accessTokenService;

    private final PasswordEncoder passwordEncoder;

    private final TransactionOperations txOps;

    @Override
    public AccessToken signIn(CandidateSignInDto dto) {
        Candidate candidate = candidateRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException(""));
        if (!passwordEncoder.matches(dto.getPassword(), candidate.getPasswordHash())) {
            throw new BadCredentialsException("");
        }
        CandidatePrincipal principal = CandidatePrincipal.from(candidate);
        return accessTokenService.generate(principal);
    }

    @Override
    public AccessToken signUp(CandidateSignUpDto dto) {
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        Candidate candidate = create(dto, passwordHash);
        CandidatePrincipal principal = CandidatePrincipal.from(candidate);
        return accessTokenService.generate(principal);
    }

    private Candidate create(CandidateSignUpDto dto, String passwordHash) {
        return txOps.execute(tx -> {
            boolean existByEmail = candidateRepo.findByEmail(dto.getEmail()).isPresent();
            if (existByEmail) {
                throw new BusinessException("");
            }
            Cv cv = new Cv()
                    .setContent(dto.getCv().getContent());
            Candidate candidate = new Candidate()
                    .setEmail(dto.getEmail())
                    .setPasswordHash(passwordHash)
                    .setFirstName(dto.getFirstName())
                    .setLastName(dto.getLastName())
                    .setCv(cv);
            candidateRepo.create(candidate);
            return candidate;
        });
    }
}
