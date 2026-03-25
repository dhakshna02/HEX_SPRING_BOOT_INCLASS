package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.TicketMapper;
import com.springboot.march_24_1sb.Repository.TicketRepository;
import com.springboot.march_24_1sb.dto.GetFilterDto;
import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.dto.TicketGetDto;
import com.springboot.march_24_1sb.dto.TicketResDto;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Ticket;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;



    public Ticket saveToDb( TicketDto ticketDto) {

        Ticket ticket =  TicketMapper.dtoToEntityMapper(ticketDto);
        ticket.setTicketStatus(TicketStatus.OPEN);

        return ticketRepository.save(ticket);
    }

    public TicketGetDto GetAllTicket(int page, int size) {

        Pageable page1 = PageRequest.of(page, size);
        Page<Ticket> TicketPage = ticketRepository.findAll(page1);
        int TotalPages = TicketPage.getTotalPages();
        long TotalElements = TicketPage.getTotalElements();

        List<TicketResDto> ListOfTicketResponseDto = TicketPage
                                                              .toList()
                                                              .stream()
                                                              .map(TicketMapper ::entityToDto )
                                                              .toList();


        return new TicketGetDto(
                ListOfTicketResponseDto,        // TicketPage.toList(),   // i need to change it to the List<TicketResDto because in this i exposes whole entity also because we might have fk wthic may have user password so take tha each dto and then give it in list
                TotalPages,
                TotalElements

        );

    }

    public TicketResDto getById(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow( () -> new ResourceNotFound("Invalid id"));

        return new  TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketStatus(),
                ticket.getTicketPriority(),
                ticket.getCreatedAt()
        );


    }

    public List<Ticket> getTicketUsingFilter(GetFilterDto getFilterDto) {

         TicketStatus status = (getFilterDto.status() != null && !getFilterDto.status().isEmpty())
             ? TicketStatus.valueOf(getFilterDto.status()) : null ;


         TicketPriority priority = (getFilterDto.priority() != null && !getFilterDto.priority().isEmpty())
                 ? TicketPriority.valueOf(getFilterDto.priority()) : null;

         return ticketRepository.getTicketUsingFilter(status,priority);



    }
}
