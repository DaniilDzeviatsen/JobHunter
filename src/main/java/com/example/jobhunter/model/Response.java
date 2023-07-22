package com.example.jobhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "response")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cover_letter")
    private String coverLetter;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn (name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
