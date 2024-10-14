package com.jung.Ssave.user.service;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jung.Ssave.common.Encrypt;
import com.jung.Ssave.user.domain.User;
import com.jung.Ssave.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository){
		
		this.userRepository = userRepository;
	}



	public User addUser(String loginId, String name, String password, String phoneNumber){
		
		Encrypt encrypt = new Encrypt();
		
		String salt = encrypt.getSalt();
		String encryptPassword = encrypt.getEncrypt(password, salt);
		
		User user = User.builder()
				        .loginId(loginId)
				        .name(name)
				        .password(encryptPassword)
				        .salt(salt)
				        .phoneNumber(phoneNumber)
				        .build();
		
		User result  = userRepository.save(user);
		
		return result;
		
		
	}
	
	
	public User getUser(String loginId, String password) {
		
		Encrypt encrypt = new Encrypt();
		
		User user = userRepository.findByLoginId(loginId);
		
		String encryptPassword = encrypt.getEncrypt(password, user.getSalt());
		
		return userRepository.findByLoginIdAndPassword(loginId, encryptPassword);
	
	}
	
	
	
	
	public Boolean isDuplicateLoginId(String loginId) {
		
		int count = userRepository.countByLoginId(loginId);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public User getUserById(int id){
		
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.orElse(null);
		
		return user;
		
	}
	
	
	
	
}
