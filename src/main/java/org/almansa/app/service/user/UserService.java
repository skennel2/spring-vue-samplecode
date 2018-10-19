package org.almansa.app.service.user;

import java.util.List;
import java.util.Objects;

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
	public void addUser(User user) throws UserJoinException {
		verifyToJoinUser(user);
		
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
	
	@Transactional(readOnly = true)
	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(()->{
			return new EntityNotFoundException();
		});
	}
	
	@Transactional(readOnly = true)
	public User getByEmail(String email) {
		List<User> users = userRepository.findByEmail(email);
		if(users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	@Transactional(readOnly = true)
	public boolean checkAuthWithLoginParameter(String email, String password) {
		User user = this.getByEmail(email);
		if(user != null) {			
			return user.getPassword().equals(password);
		}else {
			return false;
		}
	}
	
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	private void verifyToJoinUser(User newUser) throws UserJoinException {
		verifyUserRequiredValue(newUser);
		verifyNotDuplicatedEmail(newUser.getEmail());
	}
	
	private void verifyNotDuplicatedEmail(String email) throws UserJoinException {
		List<User> duplicatedEmailUsers = userRepository.findByEmail(email);
		if(duplicatedEmailUsers.size() > 0) {
			throw new UserJoinException();
		}
	}
	
	private void verifyUserRequiredValue(User user) throws UserJoinException {
		try {
			Objects.requireNonNull(user);
			Objects.requireNonNull(user.getEmail());
			Objects.requireNonNull(user.getPassword());
		}catch(NullPointerException ex) {
			throw new UserJoinException(ex);
		}
	}
}
