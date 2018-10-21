package org.almansa.app.web.security.provider;

import org.almansa.app.service.user.UserAccountService;
import org.almansa.app.web.security.token.PostAuthToken;
import org.almansa.app.web.security.token.PreAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider{

	@Autowired
	private UserAccountService userAccountService;
	
	/*
	 * 인증 전 객체를 받아서 인증된 객체로 돌려준다.
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		PreAuthToken preAuthToken = (PreAuthToken)authentication;
		
		String email = preAuthToken.getUserEmail();
		String password = preAuthToken.getUserPassword();
		
		if(userAccountService.checkAuthWithLoginParameter(email, password)) {
			return new PostAuthToken(email, password);
		}else {
			throw new IllegalStateException("인증정보가 잘못되었습니다.");
		}
	}

	/*
	 * 이 인증 제공자가 지원하는 클래스
	 * 여기서는 인증전 토큰
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthToken.class.isAssignableFrom(authentication);
	}

}
