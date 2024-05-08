package com.example.demo.project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

	@Autowired
	private AthenticateService service;

	@PostMapping("register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterUserRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

}
