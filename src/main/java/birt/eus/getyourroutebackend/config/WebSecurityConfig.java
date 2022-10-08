package birt.eus.getyourroutebackend.config;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

  @Bean
  @Profile("!dev")
  SecurityFilterChain appSecurity(HttpSecurity http) throws Exception {
    http.csrf().disable()
      //Each request is treated in isolation
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      //Endpoint authorization
      .authorizeHttpRequests(request -> {
        request.antMatchers("/api/v0/authentication/**").permitAll();
        request.anyRequest().authenticated();
      });

    return http.build();
  }

  @Bean
  UserDetailsService userDetailsService(UserRepository userRepository) {
    return email -> {
      User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("No user with the " + email + " found in the db"));
      return org.springframework.security.core.userdetails.User
        .withUsername(user.getName())
        .password(user.getPassword())
        .build();
    };
  }

  // Disable authentication in dev environment
  @Bean
  @Profile("dev")
  SecurityFilterChain appSecurityDev(HttpSecurity http) throws Exception {
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests().anyRequest().permitAll();

    return http.build();
  }
}
