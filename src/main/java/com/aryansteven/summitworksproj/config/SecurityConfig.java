package com.aryansteven.summitworksproj.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aryansteven.summitworksproj.service.NgoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  NgoUserService userServ;
  private Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaults = h -> {
  };

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userServ);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(
        ar -> ar.antMatchers(HttpMethod.POST, "/session").permitAll().antMatchers(HttpMethod.POST, "/users", "/tickets")
            .hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.POST, "/events").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.GET, "/events/**", "/session").hasAnyAuthority("USER", "ADMIN")
            .antMatchers(HttpMethod.GET, "/users/**", "/tickets/**").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/session").hasAnyAuthority("USER", "ADMIN")
            .antMatchers(HttpMethod.DELETE, "/users/*", "/events/*").hasAnyAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/users/*", "/events/*").hasAuthority("ADMIN"))
        .httpBasic(h -> h.authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint()))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(c -> c.disable())
        .exceptionHandling(e -> e.accessDeniedPage("/403"));

    http.addFilterBefore(new CheckAuthCookieFilter(), BasicAuthenticationFilter.class);
  }

  @Autowired
  public void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }

}

class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
  }
}