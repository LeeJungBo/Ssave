package com.jung.Ssave.user.service;

import org.springframework.stereotype.Service;

import com.jung.Ssave.user.domain.User;
import com.jung.Ssave.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository){
		
		this.userRepository = userRepository;
	
	}



	public int addUser(String loginId, String name, String password, String phoneNumber){
		
		int count = userRepository.insertUser(loginId, name, password, phoneNumber);
		
		return count;
		
	}
	
	public User getUser(String loginId, String paassword) {
		
		return userRepository.selectUser(loginId, paassword);
		
	}
	
	public Boolean isDuplicateLoginId(String loginId) {
		
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	
	
	
}
