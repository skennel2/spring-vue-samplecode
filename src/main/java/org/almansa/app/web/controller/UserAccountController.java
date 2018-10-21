package org.almansa.app.web.controller;

import java.util.List;

import org.almansa.app.domain.UserAccount;
import org.almansa.app.domain.UserAccountDto;
import org.almansa.app.service.user.UserAccountDtoAssembler;
import org.almansa.app.service.user.UserJoinException;
import org.almansa.app.service.user.UserAccountService;
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
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserAccountController {

	private UserAccountService userService;

	private UserAccountDtoAssembler userAssembler;

	@Autowired
	public UserAccountController(UserAccountService userService, UserAccountDtoAssembler userAssembler) {
		super();
		this.userService = userService;
		this.userAssembler = userAssembler;
	}

	@PostMapping(path = "/join")
	public ResponseEntity<Void> join(@RequestBody UserAccount newUser) throws UserJoinException {
		try {
			userService.addUser(newUser);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserJoinException ex) {
			throw ex;
		}
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<Void> login(@RequestParam String email, @RequestParam String password) throws UserJoinException {
		if(userService.checkAuthWithLoginParameter(email, password)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}	

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserAccountDto> getById(@PathVariable(required = true) Long id) {
		UserAccount user = userService.getById(id);
		return new ResponseEntity<>(userAssembler.convert(user), HttpStatus.OK);
	}

	@GetMapping(path = "/email")
	public ResponseEntity<UserAccountDto> getByEmail(@RequestParam String email) {
		UserAccount user = userService.getByEmail(email);
		return new ResponseEntity<>(userAssembler.convert(user), HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<UserAccountDto>> getAllUsers() {
		List<UserAccountDto> userDtos = userAssembler.convert(userService.getAll());
		return new ResponseEntity<List<UserAccountDto>>(userDtos, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(required = true) Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
