package com.aryansteven.summitworksproj.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class NgoTicket implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToMany(mappedBy="ticket")
  private Set<NgoEvent> events;

  @ManyToOne
  @JoinColumn(name="id", nullable= false)
  private NgoUser user;

  @Column(name="event_id")
  private String event_id;

  @Column(name="first_name")
  private String first_name;
  
  @Column(name="email")
  private String email;
  
  @Column(name="contact_number")
  private Integer contact_number;

  @Column(name="address")
  private String address;
  
  @Column(name="adult_qty")
  private Integer adult_qty;
  
  @Column(name="child_qty")
  private Integer child_qty;
  

  public NgoTicket() {
  }

  public NgoTicket(Integer id, NgoUser user_id, String event_id, String first_name, String email, Integer contact_number, String address, Integer adult_qty, Integer child_qty) {
    this.id = id;
    this.user = user_id;
    this.event_id = event_id;
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

  public NgoUser getUser_id() {
    return this.user;
  }

  public void setUser_id(NgoUser user_id) {
    this.user = user_id;
  }

  public String getEvent_id() {
    return this.event_id;
  }

  public void setEvent_id(String event_id) {
    this.event_id = event_id;
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

  public NgoTicket user_id(NgoUser user_id) {
    setUser_id(user_id);
    return this;
  }

  public NgoTicket event_id(String event_id) {
    setEvent_id(event_id);
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
        return Objects.equals(id, ngoTicket.id) && Objects.equals(user, ngoTicket.user) && Objects.equals(event_id, ngoTicket.event_id) && Objects.equals(first_name, ngoTicket.first_name) && Objects.equals(email, ngoTicket.email) && Objects.equals(contact_number, ngoTicket.contact_number) && Objects.equals(address, ngoTicket.address) && Objects.equals(adult_qty, ngoTicket.adult_qty) && Objects.equals(child_qty, ngoTicket.child_qty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, event_id, first_name, email, contact_number, address, adult_qty, child_qty);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", user_id='" + getUser_id() + "'" +
      ", event_id='" + getEvent_id() + "'" +
      ", first_name='" + getFirst_name() + "'" +
      ", email='" + getEmail() + "'" +
      ", contact_number='" + getContact_number() + "'" +
      ", address='" + getAddress() + "'" +
      ", adult_qty='" + getAdult_qty() + "'" +
      ", child_qty='" + getChild_qty() + "'" +
      "}";
  }





  


}
