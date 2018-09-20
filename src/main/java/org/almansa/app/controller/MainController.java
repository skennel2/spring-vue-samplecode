package org.almansa.app.controller;

import java.util.List;

import org.almansa.app.domain.Post;
import org.almansa.app.domain.User;
import org.almansa.app.service.PostService;
import org.almansa.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/main")
public class MainController {

	@Autowired
	private PostService postService;	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/post/add")
	public ResponseEntity<Void> addPost(@ModelAttribute Post post) {

		postService.addPost(post);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getById(@PathVariable Long id) {

		return new ResponseEntity<Post>(postService.getById(id).get(), HttpStatus.OK);
	}
	
	@GetMapping("/post/all")
	public ResponseEntity<List<Post>> getAll() {

		return new ResponseEntity<List<Post>>(postService.getAll(), HttpStatus.OK);
	}	
	
	@DeleteMapping("/post/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		postService.deleteById(id);		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/user/add")
	public ResponseEntity<Void> addUser(@ModelAttribute User user){
		userService.addUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
