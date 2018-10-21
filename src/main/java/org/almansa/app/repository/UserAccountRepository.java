package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	List<UserAccount> findByEmail(String email);
}
