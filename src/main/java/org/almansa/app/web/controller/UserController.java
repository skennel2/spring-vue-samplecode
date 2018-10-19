package org.almansa.app.web.controller;

import java.util.List;

import org.almansa.app.domain.User;
import org.almansa.app.domain.UserDto;
import org.almansa.app.service.user.UserDtoAssembler;
import org.almansa.app.service.user.UserJoinException;
import org.almansa.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

	private UserService userService;

	private UserDtoAssembler userAssembler;

	@Autowired
	public UserController(UserService userService, UserDtoAssembler userAssembler) {
		super();
		this.userService = userService;
		this.userAssembler = userAssembler;
	}

	@PostMapping(path = "/join")
	public ResponseEntity<Void> joinUser(@RequestBody User newUser) throws UserJoinException {
		try {
			userService.addUser(newUser);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserJoinException ex) {
			throw ex;
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable(required = true) Long id) {
		User user = userService.getById(id);
		return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
	}

	@GetMapping(path = "/email")
	public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
		User user = userService.getByEmail(email);
		return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> userDtos = userAssembler.convert(userService.getAll());
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(required = true) Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
