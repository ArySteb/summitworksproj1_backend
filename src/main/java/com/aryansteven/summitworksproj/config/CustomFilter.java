package com.aryansteven.summitworksproj.config;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aryansteven.summitworksproj.service.NgoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomFilter extends OncePerRequestFilter {

  NgoUserService userServ;

  BCryptPasswordEncoder passwordEncoder;

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String prefix = "Basic ";
    String authToken = request.getHeader("Authorization");

    if (!authToken.startsWith(prefix)) {
      throw new ServletException();
    }

    authToken = authToken.substring(prefix.length());

    String decodedToken = new String(Base64.getDecoder().decode(authToken));

    String[] splitToken = decodedToken.split(":");

    if (splitToken.length != 2) {
      throw new ServletException();
    }

    String encodedPassword = passwordEncoder.encode(splitToken[1]);

    String actualPassword = userServ.getByEmail(splitToken[0]).map((user) -> user.getPassword())
        .orElseThrow(() -> new ServletException());

    if (encodedPassword.equals(actualPassword)) {
      chain.doFilter(request, response);
    }

    return;

  }

  @Autowired
  void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }

  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}