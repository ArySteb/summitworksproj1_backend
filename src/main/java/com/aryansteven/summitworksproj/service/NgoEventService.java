package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoEvent;

public interface NgoEventService {
  public List<NgoEvent> getAll();

  public Optional<NgoEvent> getById(Integer id);

  public NgoEvent addEvent(NgoEvent event);

  public Optional<NgoEvent> updateEvent(NgoEvent event, Integer id);

  public void delEventById(Integer id);
}
