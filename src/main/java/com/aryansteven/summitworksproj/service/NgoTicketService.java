package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoTicket;

public interface NgoTicketService {
  public List<NgoTicket> getAll();

  public Optional<NgoTicket> getById(Integer id);

  public NgoTicket addTicket(NgoTicket ticket);

  public Optional<NgoTicket> updateTicket(NgoTicket ticket, Integer id);

  public void delTicketById(Integer id);
}
