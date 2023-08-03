package com.example.jobhunter.model.candidate;

import com.example.jobhunter.model.candidate.Candidate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "cv")
@Getter
@Setter
@Accessors(chain = true)
public class Cv {
    @Id
    @Column(name = "id", nullable = false)
    private Long candidateId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId
    private Candidate candidate;

    @Column (name = "content", nullable = false)
    private String content;
}
