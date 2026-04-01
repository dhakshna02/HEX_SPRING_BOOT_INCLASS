package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.DtoForGetAllByCustomer_ForRealtionship;
import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.dto.TicketResDto;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.Executive;
import com.springboot.march_24_1sb.model.Ticket;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TicketMapper {

    public static Ticket dtoToEntityMapper(TicketDto ticketDto){
    Ticket ticket = new Ticket();
    ticket.setSubject(ticketDto.subject());
    ticket.setDetails(ticketDto.details());
    ticket.setTicketPriority(ticketDto.ticket());

            return ticket;
    }

    public static TicketResDto entityToDto(Ticket ticket){
       return new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketStatus(),
                ticket.getTicketPriority(),
                ticket.getCreatedAt()
        );
    }


    public static  DtoForGetAllByCustomer_ForRealtionship MapperForGetAllByCustomer_ForRealtionship(Ticket ticket){
        return new DtoForGetAllByCustomer_ForRealtionship(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketStatus(),
                ticket.getTicketPriority(),
                ticket.getCreatedAt(),
                ticket.getCustomer().getName(),
                ticket.getExecutive() == null ? null :ticket.getExecutive().getName() ,
                ticket.getExecutive() == null ? null :ticket.getExecutive().getJobTitle()
        );




    }
}
