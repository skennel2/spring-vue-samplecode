package org.almansa.app.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.almansa.app.domain.UserAccount;
import org.almansa.app.domain.UserAccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDtoAssembler implements Converter<UserAccount, UserAccountDto>{

	@Override
	public UserAccountDto convert(UserAccount user) {
		if(user == null) {
			return null;
		}
		return new UserAccountDto(user.getId(), user.getEmail(), user.getCreationDate());
	}

	public List<UserAccountDto> convert(List<UserAccount> users){
		List<UserAccountDto> userDtos = users.stream().map((user) -> {
			if(user == null) {
				return null;
			}
			return convert(user);
		}).collect(Collectors.toList());
		
		return userDtos;
	}
}
