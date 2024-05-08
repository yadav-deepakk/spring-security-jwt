package com.example.demo.project.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.project.services.impl.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtilService jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		log.info("header: {}", authHeader);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {

			final String jwtToken = authHeader.substring(7);
			final String userEmail = jwtUtil.extractUsernameFromToken(jwtToken);

			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetail = userDetailsServiceImpl.loadUserByUsername(userEmail);

				if (jwtUtil.isTokenValid(jwtToken, userDetail)) {

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetail, null, userDetail.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);

				} else
					log.info("jwt-token validation failed!");
			}

		} else {
			log.info("Invalid Header");
		}

		filterChain.doFilter(request, response);

	}

}
