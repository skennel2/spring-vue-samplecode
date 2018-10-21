package org.almansa.app.web.security.jwt;

import org.almansa.app.domain.UserAccount;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class UserAccoutJwtFactory {
	private String secretKey = "thisiskey";
	
	public String generateToken(UserAccount userAccount) {
		String token = "";
		try {
			token = JWT.create()
					.withIssuer("nayunsu")
					.withClaim("USER_ID", userAccount.getId())
					.sign(signAlgorithm());
		} catch (Exception e) {
			throw e;
		}
		
		return token;
	}

	private Algorithm signAlgorithm() {
		return Algorithm.HMAC256(secretKey);
	}
}
