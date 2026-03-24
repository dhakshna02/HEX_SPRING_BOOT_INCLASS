package com.springboot.march_24_1sb.model;

import com.springboot.march_24_1sb.enums.JobTitle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.IdentityHashMap;

@Entity
@Table(name = "executive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Executive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_title")
    private JobTitle jobTitle;
}
