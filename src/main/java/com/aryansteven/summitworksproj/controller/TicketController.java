package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.service.NgoTicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    NgoTicketService ticketServ;


    @GetMapping("/tickets")
    List<NgoTicket> getAll() {
        return ticketServ.getAll();
      }

    @PostMapping("/tickets")
    NgoTicket postTicket(@RequestBody NgoTicket newTicket){
        return ticketServ.addTicket(newTicket);
    }

    @PutMapping("/tickets/{id}")
    Optional<NgoTicket> putTicket(@RequestBody NgoTicket ticket, @PathVariable Integer id){
        
        return ticketServ.updateTicket(ticket, id);
        
    }

    @DeleteMapping("/tickets/{id}")
    void deleteTicket(@PathVariable Integer id){
        ticketServ.delTicketById(id);
    }


    @Autowired
    public void setTicketServ(NgoTicketService ticketServ) {
        this.ticketServ = ticketServ;
    }





}
