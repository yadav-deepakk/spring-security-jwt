package com.example.demo.project.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.project.models.User;
import com.example.demo.project.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/current-user")
	public String dispalyCurrentUser(Principal principal) {
		return principal.getName();
	}

}
