package com.aryansteven.summitworksproj.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    if (cookies != null) {
      // System.out.println("whaa");
      for (Cookie cookie : cookies) {
        // System.out.println(cookie.getName() + ":" + cookie.getValue());
        if (cookie.getName().equalsIgnoreCase("ngo_authtoken")) {
          mutableRequest.putHeader("Authorization", "Basic " + cookie.getValue());
        }
      }
    } else {
      // System.out.println("no cookies");
    }
    chain.doFilter(mutableRequest, response);
  }
}