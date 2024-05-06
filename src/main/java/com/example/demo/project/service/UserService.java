package com.example.demo.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.project.models.User;

@Service
public class UserService {

	List<User> userStore = new ArrayList<>();

	public UserService() {
		userStore.add(new User(UUID.randomUUID().toString(), "deep_shah", "deep@shah.com", ""));
		userStore.add(new User(UUID.randomUUID().toString(), "meet_shah", "meet@shah.com", ""));
		userStore.add(new User(UUID.randomUUID().toString(), "rajesh", "rajesh@singh.com", ""));
		userStore.add(new User(UUID.randomUUID().toString(), "vishal", "vishal@email.com", ""));
	}

	public List<User> getAllUsers() {
		return userStore;
	}

	public Optional<User> getUserByUsername(String username) {
		for (User user : userStore) {
			if (user != null && user.getUsername().equals(username)) {
				return Optional.of(user);
			}
		}
		return null;
	}

}
