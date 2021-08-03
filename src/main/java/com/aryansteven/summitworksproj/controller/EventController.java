package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoEvent;
import com.aryansteven.summitworksproj.service.NgoEventService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
public class EventController {

  NgoEventService eventServ;

  @ApiOperation(value = "/events", tags = "Event Controller", notes = "Get all Events")
  @GetMapping("/events")
  List<NgoEvent> getAll() {
    return eventServ.getAll();
  }

  @ApiOperation(value = "/events/{id}", tags = "Event Controller", notes = "Get a specific Event")
  @GetMapping("/events/{id}")
  NgoEvent getEvent(@PathVariable Integer id) {
    Optional<NgoEvent> result = eventServ.getById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The event wasn't found");
    }

  }

  @ApiOperation(value = "/events/{id}", tags = "Event Controller", notes = "Post an Event")
  @PostMapping("/events")
  NgoEvent postEvent(@RequestBody NgoEvent newEvent) {
    return eventServ.addEvent(newEvent);
  }

  @ApiOperation(value = "/events/{id}", tags = "Event Controller", notes = "Put a specific Event")
  @PutMapping("/events/{id}")
  Optional<NgoEvent> putEvent(@RequestBody NgoEvent event, @PathVariable Integer id) {

    return eventServ.updateEvent(event, id);

  }

  @ApiOperation(value = "/events/{id}", tags = "Event Controller", notes = "Delete a specific Event")
  @DeleteMapping("/events/{id}")
  void deleteEvent(@PathVariable Integer id) {
    eventServ.delEventById(id);
  }

  @Autowired
  public void setEventServ(NgoEventService eventServ) {
    this.eventServ = eventServ;
  }
}
