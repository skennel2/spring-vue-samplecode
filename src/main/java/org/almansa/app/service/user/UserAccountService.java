package org.almansa.app.service.user;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.UserAccount;
import org.almansa.app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

	private UserAccountRepository userRepository;

	@Autowired
	public UserAccountService(UserAccountRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Transactional
	public void addUser(UserAccount user) throws UserJoinException {
		verifyToJoinUser(user);
		
		userRepository.save(user);
	}

	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<UserAccount> getAll() {
		return userRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public UserAccount getById(Long id) {
		return userRepository.findById(id).orElseThrow(()->{
			return new EntityNotFoundException();
		});
	}
	
	@Transactional(readOnly = true)
	public UserAccount getByEmail(String email) {
		List<UserAccount> users = userRepository.findByEmail(email);
		if(users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	@Transactional(readOnly = true)
	public boolean checkAuthWithLoginParameter(String email, String password) {
		UserAccount user = this.getByEmail(email);
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
	
	private void verifyToJoinUser(UserAccount newUser) throws UserJoinException {
		verifyUserRequiredValue(newUser);
		verifyNotDuplicatedEmail(newUser.getEmail());
	}
	
	private void verifyNotDuplicatedEmail(String email) throws UserJoinException {
		List<UserAccount> duplicatedEmailUsers = userRepository.findByEmail(email);
		if(duplicatedEmailUsers.size() > 0) {
			throw new UserJoinException();
		}
	}
	
	private void verifyUserRequiredValue(UserAccount user) throws UserJoinException {
		try {
			Objects.requireNonNull(user);
			Objects.requireNonNull(user.getEmail());
			Objects.requireNonNull(user.getPassword());
		}catch(NullPointerException ex) {
			throw new UserJoinException(ex);
		}
	}
}
