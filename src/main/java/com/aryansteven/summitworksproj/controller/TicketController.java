package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.service.NgoTicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;

@RestController
public class TicketController {
    NgoTicketService ticketServ;

    @ApiOperation(value = "/tickets", tags = "Ticket Controller", notes = "Get all Tickets")
    @GetMapping("/tickets")
    List<NgoTicket> getAll() {
        return ticketServ.getAll();
    }

    @ApiOperation(value = "/tickets", tags = "Ticket Controller", notes = "Post a Ticket")
    @PostMapping("/tickets")
    NgoTicket postTicket(@RequestBody NgoTicket newTicket) {
        return ticketServ.addTicket(newTicket);
    }

    @ApiOperation(value = "/tickets/{id}", tags = "Ticket Controller", notes = "Get a specific Ticket")
    @GetMapping("/tickets/{id}")
    NgoTicket getTicket(@PathVariable Integer id) {
        Optional<NgoTicket> result = ticketServ.getById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ticket wasn't found");
        }

    }

    @ApiOperation(value = "/tickets/{id}", tags = "Ticket Controller", notes = "Put a specific Ticket")
    @PutMapping("/tickets/{id}")
    Optional<NgoTicket> putTicket(@RequestBody NgoTicket ticket, @PathVariable Integer id) {

        return ticketServ.updateTicket(ticket, id);

    }

    @ApiOperation(value = "/tickets/{id}", tags = "Ticket Controller", notes = "Delete a Ticket")
    @DeleteMapping("/tickets/{id}")
    void deleteTicket(@PathVariable Integer id) {
        ticketServ.delTicketById(id);
    }

    @Autowired
    public void setTicketServ(NgoTicketService ticketServ) {
        this.ticketServ = ticketServ;
    }

}
