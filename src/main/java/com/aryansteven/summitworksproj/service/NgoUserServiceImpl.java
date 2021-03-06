package com.aryansteven.summitworksproj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.repo.NgoUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NgoUserServiceImpl implements NgoUserService {
  NgoUserRepo userRepo;

  BCryptPasswordEncoder passwordEncoder;

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
    return userRepo.save(user.id(null).password(passwordEncoder.encode(user.getPassword())));
  }

  @Override
  public Optional<NgoUser> updateUser(NgoUser user, Integer id) {
    return userRepo.findById(id).map(_old -> userRepo.save(user.id(id)));
  }

  @Override
  public void delUserById(Integer id) {
    userRepo.findById(id).ifPresent(userRepo::delete);
  }

  public Optional<NgoUser> getByEmail(String email) {
    return userRepo.findByEmail(email);
  }

  @Autowired
  public void setUserRepo(NgoUserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public NgoUserRepo getUserRepo() {
    return userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    if (email.equals("admin")) {
      return new User("admin", passwordEncoder.encode("password"),
          Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER")));
    }

    NgoUser user = this.userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
    if (user.getRole().equalsIgnoreCase("USER")) {
      authorities.add(new SimpleGrantedAuthority("USER"));
    }
    if (user.getRole().equalsIgnoreCase("ADMIN")) {
      authorities.add(new SimpleGrantedAuthority("ADMIN"));
      authorities.add(new SimpleGrantedAuthority("USER"));
    }

    return new User(user.getEmail(), user.getPassword(), authorities);
  }

  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}
