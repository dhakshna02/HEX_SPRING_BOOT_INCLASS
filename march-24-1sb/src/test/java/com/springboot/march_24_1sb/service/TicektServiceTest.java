package com.springboot.march_24_1sb.service;

import com.springboot.march_24_1sb.Repository.TicketRepository;
import com.springboot.march_24_1sb.Service.TicketService;
import com.springboot.march_24_1sb.dto.TicketResDto;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicektServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void getByIdWhenExits(){

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setSubject("Nothing here");
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
        ticket.setTicketPriority(TicketPriority.HIGH);
        ticket.setCreatedAt(Instant.now());



      TicketResDto d=   new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketStatus(),
                ticket.getTicketPriority(),
                ticket.getCreatedAt()
        );
        TicketResDto d1=   new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketStatus(),
               TicketPriority.LOW,
                ticket.getCreatedAt()
        );



      // when the repo is called with certain parameter instead of getting into db it give the return objec
       Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

       // check weather the ticket is null
       Assertions.assertNotNull(ticket);
       // check we are getting proper object and our obj is correct
        Assertions.assertEquals(d,ticketService.getById(1));

        // check our object should be correct to this
       Assertions.assertNotEquals(d1,ticketService.getById(1));

        // how many times db is invoked
        Mockito.verify(ticketRepository,times(2)).findById(1L);

   }

   // testing when id is not exists
    @Test
    public void getByIdWhenNotExits(){

        when(ticketRepository.findById(10L)).thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(ResourceNotFound.class , ()->{
                     ticketService.getById(10L);}
                );

        Assertions.assertEquals("Invalid id",e.getMessage());

        Mockito.verify(ticketRepository,times(1)).findById(10L);
    }

    // testing with the pagination
    // in this when ur calling the
    @Test
    public void getAllWithPagniation(){
        Ticket ticket1 = new Ticket();
        ticket1.setId(12L);
        ticket1.setSubject("test subject");
        ticket1.setTicketPriority(TicketPriority.LOW);
        ticket1.setTicketStatus(TicketStatus.OPEN);
        ticket1.setCreatedAt(Instant.now());
        Ticket ticket2 = new Ticket();
        ticket2.setId(14L);
        ticket2.setSubject("test subject");
        ticket2.setTicketPriority(TicketPriority.HIGH);
        ticket2.setTicketStatus(TicketStatus.CLOSED);
        ticket2.setCreatedAt(Instant.now());
       List<Ticket> list = List.of(ticket1,ticket2);

        // for 0 , 2
//       Page<Ticket> page1 = new PageImpl<>(list);
//       int pagee = 0 ;
//       int size = 2;
//
//        Pageable pageable = PageRequest.of(pagee,size);

        // for 0,1
        Page<Ticket> page2 = new PageImpl<>(list.subList(0,1));
        int pagee1 = 0 ;
        int size2 = 1;
        Pageable pageable1 = PageRequest.of(pagee1,size2);

       when(ticketRepository.findAll(pageable1)).thenReturn(page2);

       Assertions.assertEquals(1,ticketService.GetAllTicket(0,1).ticket().size());
       // Assertions.assertEquals(2 , ticketService.GetAllTicket(0,2).ticket().size());

    }



}

// in this type of testing we are not testing the proper data we are testing the objects and structure
