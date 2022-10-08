package birt.eus.getyourroutebackend.config;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  @Profile("!dev")
  protected SecurityFilterChain appSecurity(HttpSecurity http) throws Exception {
    http.csrf().disable()
      //Each request is treated in isolation
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      //Endpoint authorization
      .authorizeHttpRequests(request -> {
        request.antMatchers("/api/v0/authentication/**").permitAll();
        request.anyRequest().authenticated();
      }).httpBasic();

    return http.build();
  }

  @Bean
  protected UserDetailsService userDetailsService(UserRepository userRepository) {
    return email -> {
      User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("No user with the " + email + " found in the db"));
      return org.springframework.security.core.userdetails.User
        .withUsername(user.getName())
        .password(user.getPassword())
        .authorities(Collections.emptyList())
        .build();
    };
  }

  @Bean
  protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // Disable authentication in dev environment
  @Bean
  @Profile("dev")
  protected SecurityFilterChain appSecurityDev(HttpSecurity http) throws Exception {
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests().anyRequest().permitAll();

    return http.build();
  }
}
