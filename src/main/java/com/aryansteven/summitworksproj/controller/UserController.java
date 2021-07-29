package com.aryansteven.summitworksproj.controller;

import java.util.List;
import java.util.Optional;


import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.service.NgoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    NgoUserService userServ;


    @GetMapping("/user")
    List<NgoUser> getAll() {
        return userServ.getAll();
      }

    @PostMapping("/user")
    NgoUser postUser(@RequestBody NgoUser newUser){
        return userServ.addUser(newUser);
    }

    @PutMapping("/user/{id}")
    Optional<NgoUser> putUser(@RequestBody NgoUser user, @PathVariable Integer id){
        
        return userServ.updateUser(user, id);
        
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Integer id){
        userServ.delUserById(id);
    }


    @Autowired
    public void setUserServ(NgoUserService userServ) {
        this.userServ = userServ;
    }





}
