package com.aryansteven.summitworksproj.service;

import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.repo.NgoUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgoUserServiceImpl implements NgoUserService {
  NgoUserRepo userRepo;

  @Override
  public List<NgoUser> getAll() {
    return userRepo.findAll();
  }

  @Override
  public Optional<NgoUser> getById(Integer id) {
    return userRepo.findById(id);
  }

  @Override
  public NgoUser addUser(NgoUser user) {
    return userRepo.save(user.id(null));
  }

  @Override
  public Optional<NgoUser> updateUser(NgoUser user, Integer id) {
    return userRepo.findById(id).map(_old -> userRepo.save(user.id(id)));
  }

  @Override
  public void delUserById(Integer id) {
    userRepo.findById(id).ifPresent(userRepo::delete);
  }

  @Autowired
  public void setUserRepo(NgoUserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public NgoUserRepo getUserRepo() {
    return userRepo;
  }
}
