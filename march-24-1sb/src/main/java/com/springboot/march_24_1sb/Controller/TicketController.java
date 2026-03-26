package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.TicketService;
import com.springboot.march_24_1sb.dto.*;
import com.springboot.march_24_1sb.model.Ticket;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;


    // for relationship adding just add the id to the pathvaribale
    // beacuse its easy and also i dont want to touch the dto
    // its easy to send in UI
    @PostMapping("/insert/{customerid}")
    public ResponseEntity<?> insert(@Valid @RequestBody  TicketDto ticketDto,
                                    @PathVariable long customerid){
         ticketService.saveToDb(ticketDto,customerid) ;
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-all")
    public TicketGetDto getTicket(@RequestParam(value = "page",required = false,defaultValue = "1") int page ,
                                  @RequestParam(value = "size", required = false,defaultValue = "5") int size){
        return ticketService.GetAllTicket(page,size);

    }



    @GetMapping("/get-by-id/{id}")
    public TicketResDto getTicketById(@PathVariable long id ){
        return ticketService.getById(id);
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

}
