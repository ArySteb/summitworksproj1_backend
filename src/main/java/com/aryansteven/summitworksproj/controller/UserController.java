package com.aryansteven.summitworksproj.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

  NgoUserService userServ;

  @ApiOperation(value = "/users", tags = "User Controller", notes = "Getting all Users")
  @GetMapping("/users")
  List<NgoUser> getAll(@RequestParam Optional<String> email) {
    if (email.isPresent()) {
      return userServ.getByEmail(email.get()).map(user -> Arrays.asList(user)).orElse(new ArrayList<NgoUser>());
    }
    return userServ.getAll();
  }

  @ApiOperation(value = "/users", tags = "User Controller", notes = "Posting a new User")
  @PostMapping("/users")
  NgoUser postUser(@RequestBody NgoUser newUser) {
    try {
      return userServ.addUser(newUser);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user could not be created!");
    }
  }

  @ApiOperation(value = "/users/{id}", tags = "User Controller", notes = "Getting a specific User")
  @GetMapping("/users/{id}")
  NgoUser getUser(@PathVariable Integer id) {
    Optional<NgoUser> result = userServ.getById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user wasn't found");
    }

  }

  @ApiOperation(value = "/users/{id}", tags = "User Controller", notes = "Putting a specific User")
  @PutMapping("/users/{id}")
  Optional<NgoUser> putUser(@RequestBody NgoUser user, @PathVariable Integer id) {

    return userServ.updateUser(user, id);

  }

  @ApiOperation(value = "/users/{id}", tags = "User Controller", notes = "Deleting a User")
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Integer id) {
    userServ.delUserById(id);
  }

  @Autowired
  public void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }
}
