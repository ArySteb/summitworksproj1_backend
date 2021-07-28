package com.aryansteven.summitworksproj.model;

import java.io.Serializable;
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
@Table(name = "user")
public class NgoUser implements Serializable {
  
  
  @OneToMany(mappedBy="user")
  private Set<NgoTicket> tickets;


  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name="first_name")
  private String first_name;

  @Column(name="last_name")
  private String last_name;

  @Column(name="email", unique=true)
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="role")
  private String role;


  public NgoUser() {
  }

  public NgoUser(Integer id, String first_name, String last_name, String email, String password, String role) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public NgoUser id(Integer id) {
    setId(id);
    return this;
  }

  public NgoUser first_name(String first_name) {
    setFirst_name(first_name);
    return this;
  }

  public NgoUser last_name(String last_name) {
    setLast_name(last_name);
    return this;
  }

  public NgoUser email(String email) {
    setEmail(email);
    return this;
  }

  public NgoUser password(String password) {
    setPassword(password);
    return this;
  }

  public NgoUser role(String role) {
    setRole(role);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NgoUser)) {
            return false;
        }
        NgoUser ngoUser = (NgoUser) o;
        return Objects.equals(id, ngoUser.id) && Objects.equals(first_name, ngoUser.first_name) && Objects.equals(last_name, ngoUser.last_name) && Objects.equals(email, ngoUser.email) && Objects.equals(password, ngoUser.password) && Objects.equals(role, ngoUser.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, first_name, last_name, email, password, role);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", first_name='" + getFirst_name() + "'" +
      ", last_name='" + getLast_name() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", role='" + getRole() + "'" +
      "}";
  }


}
