package com.example.jobhunter.model.employer;


import com.example.jobhunter.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URI;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employer")
@Accessors(chain = true)
public class Employer extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "site_url", unique = true, nullable = false)
    private URI siteUrl;

}
