package com.springboot.march_24_1sb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String DocumentNamee;

    @ManyToOne
    private Customer customer;
}
