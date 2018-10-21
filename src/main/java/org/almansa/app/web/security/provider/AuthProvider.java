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
	 * ���� �� ��ü�� �޾Ƽ� ������ ��ü�� �����ش�.
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
			throw new IllegalStateException("���������� �߸��Ǿ����ϴ�.");
		}
	}

	/*
	 * �� ���� �����ڰ� �����ϴ� Ŭ����
	 * ���⼭�� ������ ��ū
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthToken.class.isAssignableFrom(authentication);
	}

}
