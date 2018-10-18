package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends EntityBase{

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	protected User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
