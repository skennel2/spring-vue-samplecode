package org.almansa.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.almansa.app.domain.User;
import org.almansa.app.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userSerivce) {
		super();
		this.userService = userSerivce;
	}
	
	@PostMapping(path="/join")
	public ResponseEntity<Void> joinUser(@RequestBody User newUser){
		try{
			userService.addUser(newUser);		
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	@GetMapping(path="/{id}")
	public ResponseEntity<User> getById(@PathVariable(required = true) Long id){
		try {
			User user = userService.getById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path= "/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
	}		
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<User> deleteById(@PathVariable(required = true) Long id){
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
