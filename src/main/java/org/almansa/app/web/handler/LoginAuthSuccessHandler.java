package org.almansa.app.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.almansa.app.domain.UserAccount;
import org.almansa.app.service.user.UserAccountService;
import org.almansa.app.web.security.jwt.UserAccoutJwtFactory;
import org.almansa.app.web.security.token.PostAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	UserAccoutJwtFactory jwtFactory;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		PostAuthToken postAuthToken = (PostAuthToken) authentication;

		UserAccount userAccount = userAccountService.getByEmail(postAuthToken.getUserEmail());
		String jwtToken = jwtFactory.generateToken(userAccount);
		
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.OK.value());
		response.getWriter().write(jwtToken);
	}

}
