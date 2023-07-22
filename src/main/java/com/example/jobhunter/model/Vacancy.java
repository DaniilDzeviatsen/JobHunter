package com.example.jobhunter.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacancy_name", nullable = false)
    private String vacancyName;

    @Column(nullable = false)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
