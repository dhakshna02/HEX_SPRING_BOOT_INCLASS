package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Mapper.TicketMapper;
import com.springboot.march_24_1sb.Repository.TicketRepository;
import com.springboot.march_24_1sb.dto.*;
import com.springboot.march_24_1sb.enums.Role;
import com.springboot.march_24_1sb.enums.TicketPriority;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.exception.TicketUpdatePermissionException;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.Executive;
import com.springboot.march_24_1sb.model.Ticket;

import com.springboot.march_24_1sb.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerService customerService;
    private final ExecutiveService executiveService;
    private final UserService userService;

    // save the enity to the ticket
    public Ticket saveToDb(TicketDto ticketDto, Principal principal) {

        // check weather the customerid exists
        Customer customerr = customerService.getCustomerByUserName(principal.getName());

        // if exist add the customer to the ticketEntity
        Ticket ticket =  TicketMapper.dtoToEntityMapper(ticketDto);
        ticket.setCustomer(customerr);
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

    public void assignExecutive(long ticketid, long execid) {

        Ticket ticket = ticketRepository.getById(ticketid);
        Executive executive = executiveService.getExecById(execid);
        ticket.setExecutive(executive);
        ticketRepository.save(ticket);

    }

    public List<DtoForGetAllByCustomer_ForRealtionship> getAllTicketByCustomer(long customerid) {

        List<Ticket> ticket =   ticketRepository.getAllTicketByCustomer(customerid);
                  return ticket.stream()
                               .map(TicketMapper::MapperForGetAllByCustomer_ForRealtionship)
                               .toList();

    }

    // get the customer by using username
    public List<DtoForGetAllByCustomer_ForRealtionship> getTicketByUserName(String username) {

        List<Ticket> lis = ticketRepository.getTicketByUserName(username);

            return lis.stream().map(TicketMapper :: MapperForGetAllByCustomer_ForRealtionship).toList();
    }

    // updating the ticket and checking the ownership
    public void updateTheTicketAndCheckOwnership(String userName, long ticketid, TicketStatus ticketStatus) {

        // check user is valid
        Users user = (Users) userService.loadUserByUsername(userName);

        // check ticket exists
        Ticket ticket = ticketRepository.findById( ticketid).orElseThrow(()-> new ResourceNotFound("Ticket Id is invalid"));


        // cheking weather the give ticket is actually belongs to the user

        if(user.getRole() == Role.CUSTOMER) {
            if (ticket.getCustomer().getUsers().getId() != user.getId())
                throw new TicketUpdatePermissionException("This ticket is not yours");
        }

        if(user.getRole() == Role.EXECUTIVE){
            if(ticket.getExecutive().getUsers().getId() != user.getId())
                throw  new TicketUpdatePermissionException("Executive doesn't own this ticket");

            if(ticket.getExecutive() == null)
                throw new TicketUpdatePermissionException("Ewextive is not assigned");
        }



        ticket.setTicketStatus(ticketStatus);
        ticketRepository.save(ticket);




    }


    // updating the ticket and checking the ownership also updating in jpql
    public void updateTheTicketAndCheckOwnershipUsingJpql(String name, long ticketid, TicketStatus ticketStatus) {

        // check user is valid
        Users user = (Users) userService.loadUserByUsername(name);

        // check ticket exists
        Ticket ticket = ticketRepository.findById( ticketid).orElseThrow(()-> new ResourceNotFound("Ticket Id is invalid"));


        // cheking weather the give ticket is actually belongs to the user

        if(user.getRole() == Role.CUSTOMER) {
            if (ticket.getCustomer().getUsers().getId() != user.getId())
                throw new TicketUpdatePermissionException("This ticket is not yours");
        }

        if(user.getRole() == Role.EXECUTIVE){
            if(ticket.getExecutive().getUsers().getId() != user.getId())
                throw  new TicketUpdatePermissionException("Executive doesn't own this ticket");

            if(ticket.getExecutive() == null)
                throw new TicketUpdatePermissionException("Ewextive is not assigned");
        }




        ticketRepository.updateTheTicketAndCheckOwnershipUsingJpql(ticketStatus,ticketid);




    }

    public List<StatDto> stat(String name) {

        List<Ticket> tickets= ticketRepository.getTicketByUserName(name);

        List<Ticket> openTickets = tickets.stream().filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.OPEN))
                                    .toList();

        List<Ticket> inProgressTicket = tickets.stream().filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.IN_PROGRESS))
                                        .toList();

        List<Ticket> closedTicket  = tickets.stream().filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.CLOSED)).toList();




        StatDto opendto = new StatDto(
                "OPEN Tickets",
                openTickets.size()
        );

        StatDto inProgress = new StatDto(
                "IN_PROGRESS Tickets",
                inProgressTicket.size()
        );


        StatDto closed = new StatDto(
                "CLOSED Tickets",
                closedTicket.size()
        );


        return List.of(opendto,inProgress,closed);
    }
}
