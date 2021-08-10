package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.repo.NgoTicketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgoTicketServiceImpl implements NgoTicketService {
  NgoTicketRepo ticketRepo;

  @Override
  public List<NgoTicket> getAll() {
    return ticketRepo.findAll();
  }

  @Override
  public Optional<NgoTicket> getById(Integer id) {
    return ticketRepo.findById(id);
  }

  @Override
  public NgoTicket addTicket(NgoTicket ticket) {
    return ticketRepo.save(ticket.id(null));
  }

  @Override
  public Optional<NgoTicket> updateTicket(NgoTicket ticket, Integer id) {
    return ticketRepo.findById(id).map(_old -> ticketRepo.save(ticket.id(id)));
  }

  @Override
  public void delTicketById(Integer id) {
    ticketRepo.findById(id).ifPresent(ticketRepo::delete);
  }

  @Autowired
  public void setTicketRepo(NgoTicketRepo ticketRepo) {
    this.ticketRepo = ticketRepo;
  }

  public NgoTicketRepo getTicketRepo() {
    return ticketRepo;
  }
}
