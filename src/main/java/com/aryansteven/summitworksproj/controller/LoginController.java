package com.aryansteven.summitworksproj.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.aryansteven.summitworksproj.model.NgoUser;
import com.aryansteven.summitworksproj.service.NgoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

  NgoUserService userService;
  BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/session")
  ResponseEntity<?> getProfile(final HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if (cookies == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no cookie");
    }

    for (Cookie c : cookies) {
      if (c.getName().equals("ngo_authtoken")) {
        String[] cred = new String(Base64.getDecoder().decode(c.getValue())).split(":");

        // System.out.println("cvalue " + c.getValue());
        // for (String s : cred) {
        // System.out.println("cred " + s);
        // }

        String email = cred[0];
        if (email.equals("admin")) {
          return ResponseEntity.ok(new NgoUser().email("admin").role("ADMIN"));
        }

        Optional<NgoUser> userOptional = userService.getByEmail(email).map(u -> u.password(null));
        if (userOptional.isPresent()) {
          return ResponseEntity.ok(userOptional.get());
        }
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/session")
  void login(@RequestBody @Valid Credentials credentials, final HttpServletResponse response,
      final HttpServletRequest request) {
    boolean isCorrect;

    try {
      UserDetails u = userService.loadUserByUsername(credentials.getEmail());
      isCorrect = passwordEncoder.matches(credentials.getPassword(), u.getPassword());
    } catch (UsernameNotFoundException e) {
      isCorrect = false;
    }
    if (!isCorrect) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username!");
    }

    String encodedCredentials = Base64.getEncoder()
        .encodeToString((credentials.getEmail() + ":" + credentials.getPassword()).getBytes());

    Cookie authCookie = new Cookie("ngo_authtoken", encodedCredentials);
    authCookie.setPath("/");
    authCookie.setHttpOnly(true);
    response.addCookie(authCookie);
  }

  @DeleteMapping("/session")
  void logout(final HttpServletRequest request, final HttpServletResponse response) {
    Cookie cookie = new Cookie("ngo_authtoken", null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");

    response.addCookie(cookie);
  }

  @Autowired
  public void setUserService(NgoUserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}

class Credentials {
  @NotNull
  String email;

  @NotNull
  String password;

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

  public Credentials() {
  }

  public Credentials(String email, String password) {
    this.email = email;
    this.password = password;
  }

}

class SessionToken {
  String token;

  public SessionToken() {
  }

  public SessionToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
