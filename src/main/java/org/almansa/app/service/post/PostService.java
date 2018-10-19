package org.almansa.app.service.post;

import java.util.List;
import java.util.Optional;

import org.almansa.app.domain.Post;
import org.almansa.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

	private PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Transactional
	public void addPost(Post post) {
		postRepository.save(post);
	}

	@Transactional(readOnly = true)
	public Optional<Post> getById(Long id) {
		return postRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}
}
