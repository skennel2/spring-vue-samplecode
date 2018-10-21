package org.almansa.app.web.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/*
 * 인증전 토큰
 * @author skennel
 *
 */
public class PreAuthToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 1L;

	public PreAuthToken(String userEmail, String password) {
		super(userEmail, password);
	}
	
	public PreAuthToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public String getUserEmail() {
		return (String)super.getPrincipal();
	}

	public String getUserPassword() {
		return (String)super.getCredentials();
	}
}
