package com.aryansteven.summitworksproj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.aryansteven.summitworksproj.service.NgoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  NgoUserService userService;
  BCryptPasswordEncoder passwordEncoder;

  @PostMapping("/session")
  void login(@RequestBody @Valid Credentials credentials, final HttpServletResponse response) {
    String encodedPass = passwordEncoder.encode(credentials.getPassword());
    boolean isCorrect = userService.getByEmail(credentials.getEmail()).map(u -> u.getPassword().equals(encodedPass))
        .orElse(false);
    if (!isCorrect) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"User Visible Realm\"");
      return;
    }

    String encodedCredentials = Base64.getEncoder()
        .encodeToString((credentials.getEmail() + ":" + credentials.getPassword()).getBytes());

    Cookie authCookie = new Cookie("ngoAuthToken", encodedCredentials);
    authCookie.setHttpOnly(true);
    authCookie.setSecure(true);
    response.addCookie(authCookie);
  }

  @DeleteMapping("/session")
  void logout(final HttpServletRequest request, final HttpServletResponse response) {
    Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equals("ngoAuthToken")).findAny()
        .ifPresent(c -> {
          c.setMaxAge(-1);
          c.setValue("");
          response.addCookie(c);
        });
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
