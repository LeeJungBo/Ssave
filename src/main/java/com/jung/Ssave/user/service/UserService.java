package com.jung.Ssave.user.service;

import org.springframework.stereotype.Service;

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
	
	
	
	
	
	
}
