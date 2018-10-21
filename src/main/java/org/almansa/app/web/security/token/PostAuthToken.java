package org.almansa.app.web.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PostAuthToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;

	public PostAuthToken(String userEmail, String password) {
		super(userEmail, password);
	}

	public PostAuthToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public String getUserEmail() {
		return (String) super.getPrincipal();
	}

	public String getUserPassword() {
		return (String) super.getCredentials();
	}
}
