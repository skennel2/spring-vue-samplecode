package org.almansa.app.controller;

import org.almansa.app.domain.User;
import org.almansa.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private UserService userSerivce;

	@Autowired
	public UserController(UserService userSerivce) {
		super();
		this.userSerivce = userSerivce;
	}
	
	@PutMapping(path="/join")
	public ResponseEntity<Void> joinUser(@ModelAttribute User newUser){
		try{
			userSerivce.addUser(newUser);		
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
