package com.aryansteven.summitworksproj.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Ticket Model")
@Entity
@Table(name = "ticket")
public class NgoTicket implements Serializable {
  
  @ApiModelProperty(notes = "ID of the Ticket", name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @ApiModelProperty(notes = "Name of the ticket", name = "first_name")
  @Column(name = "first_name")
  private String first_name;

  @ApiModelProperty(notes = "Email of the Ticket holder", name = "email")
  @Column(name = "email")
  private String email;

  @ApiModelProperty(notes = "Contact number of the Ticket", name = "contact_number")
  @Column(name = "contact_number")
  private Integer contact_number;

  @ApiModelProperty(notes = "Address of the Ticket")
  @Column(name = "address")
  private String address;

  @ApiModelProperty(notes = "Adult Quantity of the Ticket")
  @Column(name = "adult_qty")
  private Integer adult_qty;

  @ApiModelProperty(notes = "Child Quantity of the Ticket")
  @Column(name = "child_qty")
  private Integer child_qty;

  @ApiModelProperty(notes = "Event ID pertaining to the Ticket")
  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private NgoEvent event;

  @ApiModelProperty(notes = "User ID pertaining to the Ticket")
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private NgoUser user;

  public NgoTicket() {
  }

  public NgoTicket(Integer id, NgoEvent event, NgoUser user, String first_name, String email, Integer contact_number,
      String address, Integer adult_qty, Integer child_qty) {
    this.id = id;
    this.event = event;
    this.user = user;
    this.first_name = first_name;
    this.email = email;
    this.contact_number = contact_number;
    this.address = address;
    this.adult_qty = adult_qty;
    this.child_qty = child_qty;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public NgoEvent getEvent() {
    return this.event;
  }

  public void setEvent(NgoEvent event) {
    this.event = event;
  }

  public NgoUser getUser() {
    return this.user;
  }

  public void setUser(NgoUser user) {
    this.user = user;
  }

  public String getFirst_name() {
    return this.first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getContact_number() {
    return this.contact_number;
  }

  public void setContact_number(Integer contact_number) {
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

  public NgoTicket id(Integer id) {
    setId(id);
    return this;
  }

  public NgoTicket event(NgoEvent event) {
    setEvent(event);
    return this;
  }

  public NgoTicket user(NgoUser user) {
    setUser(user);
    return this;
  }

  public NgoTicket first_name(String first_name) {
    setFirst_name(first_name);
    return this;
  }

  public NgoTicket email(String email) {
    setEmail(email);
    return this;
  }

  public NgoTicket contact_number(Integer contact_number) {
    setContact_number(contact_number);
    return this;
  }

  public NgoTicket address(String address) {
    setAddress(address);
    return this;
  }

  public NgoTicket adult_qty(Integer adult_qty) {
    setAdult_qty(adult_qty);
    return this;
  }

  public NgoTicket child_qty(Integer child_qty) {
    setChild_qty(child_qty);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof NgoTicket)) {
      return false;
    }
    NgoTicket ngoTicket = (NgoTicket) o;
    return Objects.equals(id, ngoTicket.id) && Objects.equals(event, ngoTicket.event)
        && Objects.equals(user, ngoTicket.user) && Objects.equals(first_name, ngoTicket.first_name)
        && Objects.equals(email, ngoTicket.email) && Objects.equals(contact_number, ngoTicket.contact_number)
        && Objects.equals(address, ngoTicket.address) && Objects.equals(adult_qty, ngoTicket.adult_qty)
        && Objects.equals(child_qty, ngoTicket.child_qty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, event, user, first_name, email, contact_number, address, adult_qty, child_qty);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", event='" + getEvent() + "'" + ", user='" + getUser() + "'"
        + ", first_name='" + getFirst_name() + "'" + ", email='" + getEmail() + "'" + ", contact_number='"
        + getContact_number() + "'" + ", address='" + getAddress() + "'" + ", adult_qty='" + getAdult_qty() + "'"
        + ", child_qty='" + getChild_qty() + "'" + "}";
  }

}
