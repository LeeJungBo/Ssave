package com.jung.Ssave.user.service;


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



	public int addUser(String loginId, String name, String password, String phoneNumber){
		
		Encrypt encrypt = new Encrypt();
		
		String salt = encrypt.getSalt();
		String encryptPassword = encrypt.getEncrypt(password, salt);
		
		int count = userRepository.insertUser(loginId, name, encryptPassword, phoneNumber);
		
		return count;
		
	}
	
	
	public boolean getUser(String loginId, String password) {
		
		Encrypt encrypt = new Encrypt();
		
		User user = userRepository.selectByLoginId(loginId);
		if(user == null) {
			
			return false;
		
		}
		// user.getSalt()를 통해 기존의 salt를 이용하여 로그인시 입력한 패스워드에 기존 salt를 붙여 
		// 지금 데이터베이스에 있는 password와 비요해줄것
		String hashedPassword = encrypt.getEncrypt(password, user.getSalt()); 
		
		return hashedPassword.equals(user.getPassword());
		
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
