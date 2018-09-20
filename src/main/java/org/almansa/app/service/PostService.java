package org.almansa.app.service;

import java.util.List;
import java.util.Optional;

import org.almansa.app.domain.Post;
import org.almansa.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public void addPost(Post post) {
		postRepository.save(post);
	}
	
	public Optional<Post> getById(Long id){
		return postRepository.findById(id);
	}
	
	public List<Post> getAll(){
		return postRepository.findAll(); 
	}
}
