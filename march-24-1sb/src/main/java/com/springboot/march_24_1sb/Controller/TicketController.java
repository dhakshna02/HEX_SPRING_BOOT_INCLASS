package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.TicketService;
import com.springboot.march_24_1sb.dto.TicketDto;
import com.springboot.march_24_1sb.enums.TicketStatus;
import com.springboot.march_24_1sb.model.Ticket;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TicketController {


    private final TicketService ticketService;


    @PostMapping("/api/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody  TicketDto ticketDto){
         ticketService.saveToDb(ticketDto) ;
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
