package com.aryansteven.summitworksproj.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aryansteven.utils.MutableHttpServletRequest;

import org.springframework.web.filter.OncePerRequestFilter;

public class CheckAuthCookieFilter extends OncePerRequestFilter {

  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // System.out.println("filter called");
    MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
    // mutableRequest.removeHeader("Authorization");

    Cookie[] cookies = request.getCookies();
    boolean headerPut = false;

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("ngo_authtoken")) {
          mutableRequest.putHeader("Authorization", "Basic " + cookie.getValue());
          headerPut = true;
        }
      }
    }
    if (!headerPut) {
      mutableRequest.removeHeader("Authorization");
    }
    chain.doFilter(mutableRequest, response);
  }
}