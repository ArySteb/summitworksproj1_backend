package com.aryansteven.summitworksproj;

import javax.transaction.Transactional;

import com.aryansteven.summitworksproj.model.NgoEvent;
import com.aryansteven.summitworksproj.model.NgoTicket;
import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.service.NgoEventService;
import com.aryansteven.summitworksproj.service.NgoTicketService;
import com.aryansteven.summitworksproj.service.NgoUserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class SummitworksprojApplicationTests {

  @Test
  void contextLoads() {
  }

  NgoTicketService ticketserv;

  NgoEventService eventserv;

  NgoUserService userserv;

  @Test
  @Transactional
  @Rollback(value = true)
  void testAddTicket() {

    NgoEvent event = new NgoEvent().name("Volleyball");

    NgoUser user = new NgoUser().first_name("Aryan").email("aryan@mail.com").password("password123");
    eventserv.addEvent(event);
    userserv.addUser(user);

    NgoTicket ticket = new NgoTicket().first_name("Aryan").user(user).event(event);

    ticketserv.addTicket(ticket);
  }

  @Autowired
  public void setEventserv(NgoEventService eventserv) {
    this.eventserv = eventserv;
  }

  @Autowired
  public void setUserserv(NgoUserService userserv) {
    this.userserv = userserv;
  }

  @Autowired
  public void setTicketserv(NgoTicketService ticketserv) {
    this.ticketserv = ticketserv;
  }

}
