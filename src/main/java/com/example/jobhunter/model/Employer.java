package com.example.jobhunter.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employer")
public class Employer {

    @Column(name = "employer_name", nullable = false, unique = true)
    private String employerName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employer_email", unique = true, nullable = false)
    private String employerEmail;

    @Column(name = "website", unique = true, nullable = false)
    private String website;

}
