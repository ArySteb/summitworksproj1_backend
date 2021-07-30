package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.service.NgoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
  NgoUserService userServ;

  @GetMapping("/users")
  List<NgoUser> getAll() {
    return userServ.getAll();
  }

  @PostMapping("/users")
  NgoUser postUser(@RequestBody NgoUser newUser) {
    return userServ.addUser(newUser);
  }

  @GetMapping("/users/{id}")
  NgoUser getUser(@PathVariable Integer id) {
    Optional<NgoUser> result = userServ.getById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user wasn't found");
    }

  }

  @PutMapping("/users/{id}")
  Optional<NgoUser> putUser(@RequestBody NgoUser user, @PathVariable Integer id) {

    return userServ.updateUser(user, id);

  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Integer id) {
    userServ.delUserById(id);
  }

  @Autowired
  public void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }
}
