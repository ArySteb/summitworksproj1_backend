package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface NgoUserService extends UserDetailsService{
  public List<NgoUser> getAll();

  public Optional<NgoUser> getById(Integer id);

  public NgoUser addUser(NgoUser user);

  public Optional<NgoUser> updateUser(NgoUser user, Integer id);

  public void delUserById(Integer id);
}
