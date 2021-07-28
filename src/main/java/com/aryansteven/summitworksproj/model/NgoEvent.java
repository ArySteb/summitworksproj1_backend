package com.aryansteven.summitworksproj.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class NgoEvent implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(mappedBy = "event")
  private Set<NgoTicket> tickets;

  @Column(name = "event_name", length = 50, unique = false)
  private String name;

  @Column(name = "event_description", length = 300)
  private String desc;

  @Column(name = "category", length = 10)
  private String category;

  @Column(name = "start_date")
  private LocalDate start_date;

  @Column(name = "end_date")
  private LocalDate end_date;

  @Column(name = "start_time")
  private LocalTime start_time;

  @Column(name = "end_time")
  private LocalTime end_time;

  @Column(name = "allow_reg")
  private boolean allow_reg;

  @Column(name = "img_url")
  private String img_url;

  @Column(name = "adult_price")
  private Integer adult_price;

  @Column(name = "child_price")
  private Integer child_price;

  public NgoEvent() {
  }

  public NgoEvent(Integer id, String name, String desc, String category, LocalDate start_date, LocalDate end_date,
      LocalTime start_time, LocalTime end_time, boolean allow_reg, String img_url, Integer adult_price,
      Integer child_price) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.category = category;
    this.start_date = start_date;
    this.end_date = end_date;
    this.start_time = start_time;
    this.end_time = end_time;
    this.allow_reg = allow_reg;
    this.img_url = img_url;
    this.adult_price = adult_price;
    this.child_price = child_price;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public LocalDate getStart_date() {
    return this.start_date;
  }

  public void setStart_date(LocalDate start_date) {
    this.start_date = start_date;
  }

  public LocalDate getEnd_date() {
    return this.end_date;
  }

  public void setEnd_date(LocalDate end_date) {
    this.end_date = end_date;
  }

  public LocalTime getStart_time() {
    return this.start_time;
  }

  public void setStart_time(LocalTime start_time) {
    this.start_time = start_time;
  }

  public LocalTime getEnd_time() {
    return this.end_time;
  }

  public void setEnd_time(LocalTime end_time) {
    this.end_time = end_time;
  }

  public boolean isAllow_reg() {
    return this.allow_reg;
  }

  public boolean getAllow_reg() {
    return this.allow_reg;
  }

  public void setAllow_reg(boolean allow_reg) {
    this.allow_reg = allow_reg;
  }

  public String getImg_url() {
    return this.img_url;
  }

  public void setImg_url(String img_url) {
    this.img_url = img_url;
  }

  public Integer getAdult_price() {
    return this.adult_price;
  }

  public void setAdult_price(Integer adult_price) {
    this.adult_price = adult_price;
  }

  public Integer getChild_price() {
    return this.child_price;
  }

  public void setChild_price(Integer child_price) {
    this.child_price = child_price;
  }

  public NgoEvent id(Integer id) {
    setId(id);
    return this;
  }

  public NgoEvent name(String name) {
    setName(name);
    return this;
  }

  public NgoEvent desc(String desc) {
    setDesc(desc);
    return this;
  }

  public NgoEvent category(String category) {
    setCategory(category);
    return this;
  }

  public NgoEvent start_date(LocalDate start_date) {
    setStart_date(start_date);
    return this;
  }

  public NgoEvent end_date(LocalDate end_date) {
    setEnd_date(end_date);
    return this;
  }

  public NgoEvent start_time(LocalTime start_time) {
    setStart_time(start_time);
    return this;
  }

  public NgoEvent end_time(LocalTime end_time) {
    setEnd_time(end_time);
    return this;
  }

  public NgoEvent allow_reg(boolean allow_reg) {
    setAllow_reg(allow_reg);
    return this;
  }

  public NgoEvent img_url(String img_url) {
    setImg_url(img_url);
    return this;
  }

  public NgoEvent adult_price(Integer adult_price) {
    setAdult_price(adult_price);
    return this;
  }

  public NgoEvent child_price(Integer child_price) {
    setChild_price(child_price);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof NgoEvent)) {
      return false;
    }
    NgoEvent ngoEvent = (NgoEvent) o;
    return Objects.equals(id, ngoEvent.id) && Objects.equals(name, ngoEvent.name) && Objects.equals(desc, ngoEvent.desc)
        && Objects.equals(category, ngoEvent.category) && Objects.equals(start_date, ngoEvent.start_date)
        && Objects.equals(end_date, ngoEvent.end_date) && Objects.equals(start_time, ngoEvent.start_time)
        && Objects.equals(end_time, ngoEvent.end_time) && allow_reg == ngoEvent.allow_reg
        && Objects.equals(img_url, ngoEvent.img_url) && Objects.equals(adult_price, ngoEvent.adult_price)
        && Objects.equals(child_price, ngoEvent.child_price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, desc, category, start_date, end_date, start_time, end_time, allow_reg, img_url,
        adult_price, child_price);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", desc='" + getDesc() + "'" + ", category='"
        + getCategory() + "'" + ", start_date='" + getStart_date() + "'" + ", end_date='" + getEnd_date() + "'"
        + ", start_time='" + getStart_time() + "'" + ", end_time='" + getEnd_time() + "'" + ", allow_reg='"
        + isAllow_reg() + "'" + ", img_url='" + getImg_url() + "'" + ", adult_price='" + getAdult_price() + "'"
        + ", child_price='" + getChild_price() + "'" + "}";
  }

}
