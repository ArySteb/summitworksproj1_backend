package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;


import com.aryansteven.summitworksproj.model.NgoEvent;
import com.aryansteven.summitworksproj.service.NgoEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    NgoEventService eventServ;


    @GetMapping("/event")
    List<NgoEvent> getAll() {
        return eventServ.getAll();
      }

    @PostMapping("/user")
    NgoEvent postEvent(@RequestBody NgoEvent newEvent){
        return eventServ.addEvent(newEvent);
    }

    @PutMapping("/event/{id}")
    Optional<NgoEvent> putEvent(@RequestBody NgoEvent event, @PathVariable Integer id){
        
        return eventServ.updateEvent(event, id);
        
    }

    @DeleteMapping("/event/{id}")
    void deleteEvent(@PathVariable Integer id){
        eventServ.delEventById(id);
    }


    @Autowired
    public void setEventServ(NgoEventService eventServ) {
        this.eventServ = eventServ;
    }





}
