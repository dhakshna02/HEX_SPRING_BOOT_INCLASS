package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.TicketService;
import com.springboot.march_24_1sb.dto.*;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Ticket;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;


    // for relationship adding just add the id to the pathvaribale
    // beacuse its easy and also i dont want to touch the dto
    // its easy to send in UI
    // no need of id to insert take the username from the token then attach it
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody  TicketDto ticketDto,
                                    Principal principal){
         ticketService.saveToDb(ticketDto,principal) ;
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-all")
    public TicketGetDto getTicket(@RequestParam(value = "page",required = false,defaultValue = "1") int page ,
                                  @RequestParam(value = "size", required = false,defaultValue = "5") int size){
        return ticketService.GetAllTicket(page,size);

    }


        // this is just version1 where u get the ticktts using id
    @GetMapping("/get-by-id/{id}")
    public TicketResDto getTicketById(@PathVariable long id ){
        return ticketService.getById(id);
    }

    // getting the tickets of user whithout id by using the username from the login

    @GetMapping("/get-all-ticket-using-username")
    public List<DtoForGetAllByCustomer_ForRealtionship> getTicketByUserName(Principal principal){

        return ticketService.getTicketByUserName(principal.getName());
    }


    @PostMapping("/get-by-fliter")
    public List<Ticket> getTicketUsingFilter(@RequestBody GetFilterDto getFilterDto){
        return ticketService.getTicketUsingFilter(getFilterDto);
    }


    //here we add the ticket of the customer now we are going to assign the executive
    // to that ticket
    @PutMapping("/insert/{ticketid}/{execid}")
    public ResponseEntity<?> assignExecutive(@PathVariable long ticketid,
                                              @PathVariable long execid ){

        ticketService.assignExecutive(ticketid,execid);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }


    // Getting all the tickets of the customer in DTO

    @GetMapping("/get-all-by-customer/{customerid}")
    public List<DtoForGetAllByCustomer_ForRealtionship> getAllTicketsOfCustomer(@PathVariable long customerid){
       return ticketService.getAllTicketByCustomer(customerid);
    }



    /*
    this is for updating ticket with username
    * logic is easy pa
    * ne poi sB kita 3 things kudukra
    * un agenda just oru ticket status mathano
    * ne enala kudukra
    * who am i - user(via token )
    * Which ticket you want to change - ticket (id)
    * what you want to change - content
    *
    *
    * now sB will check wheather the ticket which you gave is valid
    * then it will check weather the give user is valid
    * then it will check weather the user is owner of the ticket
    * then it will update the content in that ticket
    *
    *
    *
    * */
    // updating of ticket

    @PostMapping("/update/{ticketid}")
    public ResponseEntity<?> updateTheTicketAndCheckOwnership(Principal principal,
                                                              @PathVariable(value = "ticketid") long ticketid,
                                                              @RequestParam TicketStatus ticketStatus
                                                               ){

        ticketService.updateTheTicketAndCheckOwnership(principal.getName(),ticketid,ticketStatus);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }




    /*
    * we can still do that but in the db level */


    @PostMapping("/update/jpql/{ticketid}")
    public ResponseEntity<?> updateTheTicketAndCheckOwnershipUsingJpql(Principal principal,
                                                              @PathVariable(value = "ticketid") long ticketid,
                                                              @RequestParam TicketStatus ticketStatus
    ){

        ticketService.updateTheTicketAndCheckOwnershipUsingJpql(principal.getName(),ticketid,ticketStatus);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
