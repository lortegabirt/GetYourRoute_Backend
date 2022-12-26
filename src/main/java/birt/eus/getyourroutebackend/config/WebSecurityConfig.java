package birt.eus.getyourroutebackend.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.UserRepository;
import birt.eus.getyourroutebackend.security.CustomUserDetails;
import birt.eus.getyourroutebackend.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  // private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
	
  @Bean
  @Profile("!dev")
  protected SecurityFilterChain appSecurity(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
    throws Exception {
    http.csrf().disable()
      //Each request is treated in isolation
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      //Endpoint authorization
      .authorizeHttpRequests(request -> {
        request.antMatchers(HttpMethod.OPTIONS).permitAll();
        request.antMatchers("actuator/**").permitAll();
        request.antMatchers("/api/v0/authentication/**").permitAll();
        request.antMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll();
        request.anyRequest().authenticated();
      });

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  protected UserDetailsService userDetailsService(UserRepository userRepository) {
    return email -> {
      User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("No user with the " + email + " found in the db"));
      return new CustomUserDetails(user.getName(), user.getPassword(), Collections.emptyList(), user.getId());
    };
  }

  @Bean
  protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  protected WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedMethods("*");
      }
    };
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
  /*
  @Bean
  public MongoCustomConversions customConversions() {
        converters.add(new LocalDateTimeReadConverter());
        converters.add(new LocalDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
  }*/
}
