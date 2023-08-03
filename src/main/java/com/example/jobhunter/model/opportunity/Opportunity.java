package com.example.jobhunter.model.opportunity;


import com.example.jobhunter.model.BaseEntity;
import com.example.jobhunter.model.employer.Employer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "opportunity")
@Accessors(chain = true)
public class Opportunity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @Column (name = "created_at", nullable = false)
    private Instant createdAt;
}
