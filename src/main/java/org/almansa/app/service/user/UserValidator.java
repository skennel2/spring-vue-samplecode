package org.almansa.app.service.user;

import org.almansa.app.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> classType) {
		return classType.equals(User.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User)object;
		//ValidationUtils.
	}

}
