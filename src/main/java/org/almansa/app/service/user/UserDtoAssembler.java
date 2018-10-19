package org.almansa.app.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.almansa.app.domain.User;
import org.almansa.app.domain.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoAssembler implements Converter<User, UserDto>{

	@Override
	public UserDto convert(User user) {
		return new UserDto(user);
	}

	public List<UserDto> convert(List<User> users){
		List<UserDto> userDtos = users.stream().map((user) -> {
			return convert(user);
		}).collect(Collectors.toList());
		
		return userDtos;
	}
}
