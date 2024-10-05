package com.jung.Ssave.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Ssave.user.service.UserService;

@RestController
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		
		this.userService = userService;
		
	}
	
	
	
	@PostMapping("/user/join")
	public Map<String, String>inputJoin(
			@RequestParam("loginId") String loginId
			, @RequestParam("name") String name
			, @RequestParam("password") String password
			, @RequestParam("phoneNumber") String phoneNumber){
		
		int count = userService.addUser(loginId, name, password, phoneNumber);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	
	
	
	
	
	
}
