package com.aryansteven.summitworksproj.config;

import com.aryansteven.summitworksproj.service.NgoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    http.authorizeRequests(ar -> ar.antMatchers(HttpMethod.POST, "/session").anonymous()
        .antMatchers(HttpMethod.POST, "/users", "/tickets").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/events").hasAuthority("ADMIN").antMatchers(HttpMethod.GET, "/events/**")
        .hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.GET, "/users/**", "/tickets/**")
        .hasAnyAuthority("ADMIN").antMatchers(HttpMethod.DELETE, "/session").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/users/*", "/events/*").hasAnyAuthority("ADMIN")
        .antMatchers(HttpMethod.PUT, "/users/*", "/events/*").hasAuthority("ADMIN")).httpBasic(withDefaults)
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(c -> c.disable())
        .exceptionHandling(e -> e.accessDeniedPage("/403"));

    http.addFilterBefore(new CheckAuthCookieFilter(), BasicAuthenticationFilter.class);
  }

  @Autowired
  public void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }

}
