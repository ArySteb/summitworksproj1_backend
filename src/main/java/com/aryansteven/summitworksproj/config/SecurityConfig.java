package com.aryansteven.summitworksproj.config;

import com.aryansteven.summitworksproj.service.NgoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  NgoUserService userServ;

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
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().anyRequest().anonymous().and();
    // TODO: make a filter that takes authcookie and puts it in auth header.
    
    // .antMatchers(HttpMethod.DELETE, "/users/*",
    // "/events/*").hasAnyAuthority("ADMIN")
    // .antMatchers(HttpMethod.POST, "/users", "/events").hasAnyAuthority("ADMIN")

    // .antMatchers(HttpMethod.PUT, "/users/*",
    // "/events/*").hasAnyAuthority("ADMIN")

    // .antMatchers(HttpMethod.GET, "/users/*", "/events",
    // "/events/*").hasAnyAuthority("USER")
    // .antMatchers(HttpMethod.POST, "/tickets").hasAnyAuthority("USER")

    // .and().formLogin().permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");
  }

  @Autowired
  public void setUserServ(NgoUserService userServ) {
    this.userServ = userServ;
  }

}
