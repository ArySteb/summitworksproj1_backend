package com.aryansteven.summitworksproj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoEvent;
import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.service.NgoEventService;
import com.aryansteven.summitworksproj.service.NgoTicketService;
import com.aryansteven.summitworksproj.service.NgoUserService;

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
  NgoEventService eventServ;
  NgoUserService userServ;

  NgoTicket convert(TicketDto input) {
    Optional<NgoUser> user = userServ.getById(input.getUserId());
    if (!user.isPresent())
      return null;
    Optional<NgoEvent> event = eventServ.getById(input.getEventId());
    if (!event.isPresent())
      return null;

    return new NgoTicket().address(input.getAddress()).adult_qty(input.getAdult_qty()).child_qty(input.getChild_qty())
        .contact_number(input.getContact_number()).email(input.getEmail()).first_name(input.getFirst_name())
        .last_name(input.getLast_name()).user(user.get()).event(event.get());

  }

  @ApiOperation(value = "/tickets", tags = "Ticket Controller", notes = "Get all Tickets")
  @GetMapping("/tickets")
  List<TicketDto> getAll() {
    return ticketServ.getAll().stream().map(TicketDto::convertTicketToDto).collect(ArrayList::new, ArrayList::add,
        ArrayList::addAll);
  }

  @ApiOperation(value = "/tickets", tags = "Ticket Controller", notes = "Post a Ticket")
  @PostMapping("/tickets")
  TicketDto postTicket(@RequestBody TicketDto newTicket) {

    return TicketDto.convertTicketToDto(ticketServ.addTicket(convert(newTicket)));
  }

  @ApiOperation(value = "/tickets/{id}", tags = "Ticket Controller", notes = "Get a specific Ticket")
  @GetMapping("/tickets/{id}")
  TicketDto getTicket(@PathVariable Integer id) {
    Optional<NgoTicket> result = ticketServ.getById(id);
    if (result.isPresent()) {
      return TicketDto.convertTicketToDto(result.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ticket wasn't found");
    }

  }

  @ApiOperation(value = "/tickets/{id}", tags = "Ticket Controller", notes = "Put a specific Ticket")
  @PutMapping("/tickets/{id}")
  Optional<TicketDto> putTicket(@RequestBody TicketDto ticket, @PathVariable Integer id) {

    return ticketServ.updateTicket(convert(ticket), id).map(TicketDto::convertTicketToDto);

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
