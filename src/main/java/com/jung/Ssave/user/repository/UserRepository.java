package com.jung.Ssave.user.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.user.domain.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	
	public User findByLoginId(String loginId);
	
	
	public int countByLoginId(String loginId);
	
	
	public User findByLoginIdAndPassword(String loginId, String password);
	
	
	
}
