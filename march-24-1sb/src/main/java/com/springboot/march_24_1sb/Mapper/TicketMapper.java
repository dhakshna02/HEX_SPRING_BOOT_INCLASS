package com.springboot.march_24_1sb.Mapper;

import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.model.Ticket;


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
}
