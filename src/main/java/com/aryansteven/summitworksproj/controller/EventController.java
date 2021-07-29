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

@RestController
public class EventController {

    NgoEventService eventServ;

    @GetMapping("/events")
    List<NgoEvent> getAll() {
        return eventServ.getAll();
    }

    @GetMapping("/events/{id}")
    NgoEvent getEvent(@PathVariable Integer id) {
        Optional<NgoEvent> result = eventServ.getById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The event wasn't found");
        }

    }

    @PostMapping("/events")
    NgoEvent postEvent(@RequestBody NgoEvent newEvent) {
        return eventServ.addEvent(newEvent);
    }

    @PutMapping("/events/{id}")
    Optional<NgoEvent> putEvent(@RequestBody NgoEvent event, @PathVariable Integer id) {

        return eventServ.updateEvent(event, id);

    }

    @DeleteMapping("/events/{id}")
    void deleteEvent(@PathVariable Integer id) {
        eventServ.delEventById(id);
    }

    @Autowired
    public void setEventServ(NgoEventService eventServ) {
        this.eventServ = eventServ;
    }
}
