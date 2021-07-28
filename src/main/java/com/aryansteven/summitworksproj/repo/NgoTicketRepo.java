package com.aryansteven.summitworksproj.repo;

import com.aryansteven.summitworksproj.model.NgoTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoTicketRepo extends JpaRepository<NgoTicket, Integer> {

}
