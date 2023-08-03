package com.example.jobhunter.model.candidate;

import com.example.jobhunter.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Entity
@Getter
@Setter
@Table(name = "candidate")
@Accessors(chain = true)
public class Candidate extends BaseEntity {


    @Column (name = "name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "candidate", optional = false, cascade = CascadeType.ALL)
    private Cv cv;

    public Candidate setCv(Cv cv){
        this.cv=cv;
        cv.setCandidate(this);
        return this;
    }
}
