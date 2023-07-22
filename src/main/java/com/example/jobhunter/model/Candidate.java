package com.example.jobhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "candidate_email", nullable = false, unique = true)
    private String candidateEmail;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "presentation")
    private String presentation;
}
