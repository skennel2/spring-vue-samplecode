package org.almansa.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.User;
import org.almansa.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(()->{
			return new EntityNotFoundException();
		});
	}
}
