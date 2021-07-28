package com.aryansteven.summitworksproj.controller;

import java.util.List;

import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.repo.NgoTicketRepo;
import com.aryansteven.summitworksproj.service.NgoTicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    NgoTicketService ticketServ;


    @GetMapping("/tickets")
    List<NgoTicket> getAll() {
        return ticketServ.getAll();
      }

    @PostMapping("/tickets")
    NgoTicket postTicket(@RequestBody NgoTicket newTicket){
        return ticketServ.addTicket(newTicket);
    }

    @Autowired
    public void setTicketServ(NgoTicketService ticketServ) {
        this.ticketServ = ticketServ;
    }





}
