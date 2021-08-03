package com.aryansteven.utils;
//* code imported from this blog:

//* https://wilddiary.com/adding-custom-headers-java-httpservletrequest/

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class MutableHttpServletRequest extends HttpServletRequestWrapper {
  // holds custom header and value mapping
  private final Map<String, String> customHeaders;
  private final Set<String> removedHeaders;

  public MutableHttpServletRequest(HttpServletRequest request) {
    super(request);
    this.customHeaders = new HashMap<String, String>();
    this.removedHeaders = new HashSet<>();
  }

  public void putHeader(String name, String value) {
    this.customHeaders.put(name, value);
  }

  public void removeHeader(String name) {
    this.customHeaders.remove(name);
    this.removedHeaders.add(name);
  }

  public String getHeader(String name) {
    // check the custom headers first
    if (removedHeaders.contains(name)) {
      return null;
    }
    String headerValue = customHeaders.get(name);

    if (headerValue != null) {
      return headerValue;
    }
    // else return from into the original wrapped object
    return ((HttpServletRequest) getRequest()).getHeader(name);
  }

  public Enumeration<String> getHeaderNames() {
    // create a set of the custom header names
    Set<String> set = new HashSet<String>(customHeaders.keySet());

    // now add the headers from the wrapped request object
    Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
    while (e.hasMoreElements()) {
      // add the names of the request headers into the list
      String n = e.nextElement();
      set.add(n);
    }

    set.removeAll(removedHeaders);
    // create an enumeration from the set and return
    return Collections.enumeration(set);
  }
}
