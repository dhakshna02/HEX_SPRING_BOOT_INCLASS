package com.springboot.march_24_1sb.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "customer_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private LocalDate createdAt;

    private LocalDate endDate;

    private BigDecimal Discount;

    private String coupon;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Plan plan;


}
