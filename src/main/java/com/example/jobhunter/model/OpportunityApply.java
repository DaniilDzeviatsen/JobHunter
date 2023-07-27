package com.example.jobhunter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "opportunity_apply")
@Accessors(chain = true)
public class OpportunityApply extends BaseEntity {

    @Column(name = "covering_letter")
    private String coveringLetter;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

    @Column (name = "created_at", nullable = false)
    private Instant createdAt;
}
