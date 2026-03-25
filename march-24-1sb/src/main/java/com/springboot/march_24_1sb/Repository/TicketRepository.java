package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.dto.GetFilterDto;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {


    @Query("""
            select t from Ticket t
            where (?1 IS NULL OR t.ticketStatus = ?1) AND
            (?2 IS NULL OR t.ticketPriority = ?2)
            """)
    List<Ticket> getTicketUsingFilter(TicketStatus status , TicketPriority priority);
}
