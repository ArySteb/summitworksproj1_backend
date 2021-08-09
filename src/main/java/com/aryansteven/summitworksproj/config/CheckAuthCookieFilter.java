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

    Cookie[] cookies = request.getCookies();
    boolean headerPut = false;

    if (cookies != null) {
      // System.out.println("whaa");
      for (Cookie cookie : cookies) {
        // System.out.println(cookie.getName() + ":" + cookie.getValue());
        if (cookie.getName().equals("ngo_authtoken")) {
          mutableRequest.putHeader("Authorization", "Basic " + cookie.getValue());
          headerPut = true;
        }
      }
    } else {
      // System.out.println("no cookies");
    }
    if (!headerPut) {
      mutableRequest.removeHeader("Authorization");
      // System.out.println("whyyy");
    }
    chain.doFilter(mutableRequest, response);
  }
}