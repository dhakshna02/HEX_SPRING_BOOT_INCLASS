package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.dto.GetFilterDto;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {


    @Query("""
            select t from Ticket t
            where (?1 IS NULL OR t.ticketStatus = ?1) AND
            (?2 IS NULL OR t.ticketPriority = ?2)
            """)
    List<Ticket> getTicketUsingFilter(TicketStatus status , TicketPriority priority);

    @Query("""
            select t from Ticket t
            where t.customer.id = ?1
            """)
    List<Ticket> getAllTicketByCustomer(long customerid);

    @Query("""
            select t from Ticket t
            where t.customer.users.username =?1
            """)
    List<Ticket> getTicketByUserName(String name);


    // modifying is too important because it make that hibernate force to update or else hibernate will think did you write
    // query wrongly (it thinks instead of select did you wrote the update)
    @Modifying
    @Transactional
    @Query("""
            update Ticket t
            set t.ticketStatus = ?1
            where t.id = ?2
            
            """)
    void updateTheTicketAndCheckOwnershipUsingJpql(TicketStatus ticketStatus, long ticketid);
}
