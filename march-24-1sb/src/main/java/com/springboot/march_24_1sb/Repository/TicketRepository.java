package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
