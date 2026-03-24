package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.TicketMapper;
import com.springboot.march_24_1sb.Repository.TicketRepository;
import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Ticket;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;



    public Ticket saveToDb( TicketDto ticketDto) {

        Ticket ticket =  TicketMapper.dtoToEntityMapper(ticketDto);
        ticket.setTicketStatus(TicketStatus.OPEN);

        return ticketRepository.save(ticket);
    }
}
