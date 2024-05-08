package com.example.demo.project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.project.repo.UserRepo;
import com.example.demo.project.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " does not found in DB"));

	}

}
