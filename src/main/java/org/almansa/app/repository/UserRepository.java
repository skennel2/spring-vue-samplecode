package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByEmail(String email);
}
