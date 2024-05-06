package com.example.demo.project.models;

public class User {
	private String id;
	private String username;
	private String email;
	private String pass;

	public User() {
		super();
	}

	public User(String id, String username, String email, String pass) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + "]";
	}

}
