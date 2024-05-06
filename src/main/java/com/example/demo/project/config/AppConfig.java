package com.example.demo.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	// create in-memory user to login
	@Bean
	public UserDetailsService userDetalils() {
		UserDetails user1 = User.builder().username("root").password(passwordEncoder().encode("toor")).roles("ADMIN")
				.build();
		UserDetails user2 = User.builder().username("deepak").password(passwordEncoder().encode("kapeed"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}

	// password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authentication manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationCfg) throws Exception {
		return authenticationCfg.getAuthenticationManager(); 
	}

}
