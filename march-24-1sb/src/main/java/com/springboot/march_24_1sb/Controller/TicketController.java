package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.TicketService;
import com.springboot.march_24_1sb.dto.GetFilterDto;
import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.dto.TicketGetDto;
import com.springboot.march_24_1sb.dto.TicketResDto;
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


    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody  TicketDto ticketDto){
         ticketService.saveToDb(ticketDto) ;
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


}
