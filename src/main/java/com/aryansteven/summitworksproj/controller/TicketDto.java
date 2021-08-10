package com.aryansteven.summitworksproj.controller;

import java.io.Serializable;
import java.util.Objects;

import com.aryansteven.summitworksproj.model.NgoTicket;

public class TicketDto implements Serializable {
  private String first_name;
  private String last_name;
  private String email;
  private String contact_number;
  private String address;
  private Integer adult_qty;
  private Integer child_qty;
  private Integer eventId;
  private Integer userId;

  public static TicketDto convertTicketToDto(NgoTicket input) {
    return new TicketDto(input.getFirst_name(), input.getLast_name(), input.getEmail(), input.getContact_number(),
        input.getAddress(), input.getAdult_qty(), input.getChild_qty(), input.getEvent().getId(),
        input.getUser().getId());
  }

  public TicketDto() {
  }

  public TicketDto(String first_name, String last_name, String email, String contact_number, String address,
      Integer adult_qty, Integer child_qty, Integer eventId, Integer userId) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.contact_number = contact_number;
    this.address = address;
    this.adult_qty = adult_qty;
    this.child_qty = child_qty;
    this.eventId = eventId;
    this.userId = userId;
  }

  public String getFirst_name() {
    return this.first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return this.last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContact_number() {
    return this.contact_number;
  }

  public void setContact_number(String contact_number) {
    this.contact_number = contact_number;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getAdult_qty() {
    return this.adult_qty;
  }

  public void setAdult_qty(Integer adult_qty) {
    this.adult_qty = adult_qty;
  }

  public Integer getChild_qty() {
    return this.child_qty;
  }

  public void setChild_qty(Integer child_qty) {
    this.child_qty = child_qty;
  }

  public Integer getEventId() {
    return this.eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public TicketDto first_name(String first_name) {
    setFirst_name(first_name);
    return this;
  }

  public TicketDto last_name(String last_name) {
    setLast_name(last_name);
    return this;
  }

  public TicketDto email(String email) {
    setEmail(email);
    return this;
  }

  public TicketDto contact_number(String contact_number) {
    setContact_number(contact_number);
    return this;
  }

  public TicketDto address(String address) {
    setAddress(address);
    return this;
  }

  public TicketDto adult_qty(Integer adult_qty) {
    setAdult_qty(adult_qty);
    return this;
  }

  public TicketDto child_qty(Integer child_qty) {
    setChild_qty(child_qty);
    return this;
  }

  public TicketDto eventId(Integer eventId) {
    setEventId(eventId);
    return this;
  }

  public TicketDto userId(Integer userId) {
    setUserId(userId);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof TicketDto)) {
      return false;
    }
    TicketDto ticketDto = (TicketDto) o;
    return Objects.equals(first_name, ticketDto.first_name) && Objects.equals(last_name, ticketDto.last_name)
        && Objects.equals(email, ticketDto.email) && Objects.equals(contact_number, ticketDto.contact_number)
        && Objects.equals(address, ticketDto.address) && Objects.equals(adult_qty, ticketDto.adult_qty)
        && Objects.equals(child_qty, ticketDto.child_qty) && Objects.equals(eventId, ticketDto.eventId)
        && Objects.equals(userId, ticketDto.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, email, contact_number, address, adult_qty, child_qty, eventId, userId);
  }

  @Override
  public String toString() {
    return "{" + " first_name='" + getFirst_name() + "'" + ", last_name='" + getLast_name() + "'" + ", email='"
        + getEmail() + "'" + ", contact_number='" + getContact_number() + "'" + ", address='" + getAddress() + "'"
        + ", adult_qty='" + getAdult_qty() + "'" + ", child_qty='" + getChild_qty() + "'" + ", eventId='" + getEventId()
        + "'" + ", userId='" + getUserId() + "'" + "}";
  }

}
