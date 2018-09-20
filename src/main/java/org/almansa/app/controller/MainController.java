package org.almansa.app.controller;

import java.util.List;

import org.almansa.app.domain.Post;
import org.almansa.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/main")
public class MainController {

	@Autowired
	private PostService postService;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addPost(@ModelAttribute Post post) {

		postService.addPost(post);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> getById(@PathVariable Long id) {

		return new ResponseEntity<Post>(postService.getById(id).get(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/post/all", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getAll() {

		return new ResponseEntity<List<Post>>(postService.getAll(), HttpStatus.OK);
	}	
}
