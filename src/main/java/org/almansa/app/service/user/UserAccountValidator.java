package org.almansa.app.service.user;

import org.almansa.app.domain.UserAccount;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserAccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> classType) {
		return classType.equals(UserAccount.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		UserAccount user = (UserAccount)object;
		//ValidationUtils.
	}

}
