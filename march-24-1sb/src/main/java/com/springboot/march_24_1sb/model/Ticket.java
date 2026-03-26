package com.springboot.march_24_1sb.model;

import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;

    @Column(length = 1000)
    private String Details;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant CreatedAt;

    @UpdateTimestamp
    private Instant UpdatedAt;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Executive executive;

}
