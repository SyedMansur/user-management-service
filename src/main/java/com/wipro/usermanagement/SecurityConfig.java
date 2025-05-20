package com.wipro.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wipro.usermanagement.component.JwtAuthFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(auth -> auth.requestMatchers("/user/**", "/product/**", "/user/**").permitAll()
//						.anyRequest().authenticated());
//		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authz -> authz.requestMatchers("/order/**", "/cart/**", "/user/**")
						.permitAll() // Allow all
						.anyRequest().permitAll() // Allow other requests without authentication
				).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

		return http.build();
	}
}
