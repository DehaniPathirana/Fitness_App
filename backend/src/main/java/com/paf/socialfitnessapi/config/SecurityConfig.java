package com.paf.socialfitnessapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
              .csrf(AbstractHttpConfigurer::disable)
              .cors((cors -> cors.configurationSource(corsConfigurationSource())))
              .authorizeHttpRequests(auth -> {
                  auth.anyRequest().permitAll();

              })
              .oauth2Login(oauth2 ->
                      oauth2.loginPage("/oauth2/authorization/google")
                              .permitAll()
//                              .defaultSuccessUrl("http://localhost:3000/home") // Redirect to home after successful login

              ).logout(t ->
                      t.logoutUrl("/logout")
                              .logoutSuccessUrl("http://localhost:3000")
                              .invalidateHttpSession(true)
                              .clearAuthentication(true)
                              .deleteCookies("JSESSIONID")
              )
              .build();
  }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);

        return urlBasedCorsConfigurationSource;
    }
}
