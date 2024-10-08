package com.jung.Ssave.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Ssave.user.domain.User;
import com.jung.Ssave.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;
	
	public UserRestController(UserService userService) {
		
		this.userService = userService;
		
	}
	
	
	
	@PostMapping("/join")
	public Map<String, String> inputJoin(
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
	
	@PostMapping("/login")
	public Map<String, Boolean> inputLogin(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password){
		
		boolean loginUser = userService.getUser(loginId, password);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("result", loginUser);
		
		return resultMap;
		
	}
	
	@GetMapping("/duplicate-loginId")
	public Map<String, Boolean> isDuplicateLoginId(
			@RequestParam("loginId") String loginId){
		
		boolean isDuplicate = userService.isDuplicateLoginId(loginId);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isDuplicate", isDuplicate);
		
		return resultMap;
	}
	
	
	
	
	
}
