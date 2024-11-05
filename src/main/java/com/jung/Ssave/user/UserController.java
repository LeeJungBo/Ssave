package com.jung.Ssave.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/join-view")
	public String join() {
		
		return "user/join";
		
	}
	
	@GetMapping("/login-view")
	public String login() {
		
		return "user/login";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("kakaoUserId");
		session.removeAttribute("kakaoNickName");
		return "redirect:/user/login-view";
	}
	
	
	
	
	
	
}
