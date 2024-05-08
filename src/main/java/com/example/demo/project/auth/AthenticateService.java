package com.example.demo.project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.project.entities.Role;
import com.example.demo.project.entities.User;
import com.example.demo.project.repo.UserRepo;
import com.example.demo.project.security.JwtUtilService;

@Service
public class AthenticateService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo repository;

	@Autowired
	private JwtUtilService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterUserRequest req) {
		User user = User.builder().firstName(req.getFirstName()).lastName(req.getLastName()).email(req.getEmail())
				.password(passwordEncoder.encode(req.getPassword())).role(Role.NORMAL_USER).build();

		repository.save(user);

		final String jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().username(req.getEmail()).token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticateRequest req) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
		User user = repository.findByEmail(req.getEmail()).orElseThrow();
		final String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().username(req.getEmail()).token(jwtToken).build();
	}

}
