package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoEvent;
import com.aryansteven.summitworksproj.repo.NgoEventRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgoEventServiceImpl implements NgoEventService {
  NgoEventRepo eventRepo;

  @Override
  public List<NgoEvent> getAll() {
    return eventRepo.findAll();
  }

  @Override
  public Optional<NgoEvent> getById(Integer id) {
    return eventRepo.findById(id);
  }

  @Override
  public NgoEvent addEvent(NgoEvent event) {
    return eventRepo.save(event.id(null));
  }

  @Override
  public Optional<NgoEvent> updateEvent(NgoEvent event, Integer id) {
    return eventRepo.findById(id).map(_old -> eventRepo.save(event.id(id)));
  }

  @Override
  public void delEventById(Integer id) {
    eventRepo.findById(id).ifPresent(eventRepo::delete);
  }

  @Autowired
  public void setEventRepo(NgoEventRepo eventRepo) {
    this.eventRepo = eventRepo;
  }

  public NgoEventRepo getEventRepo() {
    return eventRepo;
  }
}
