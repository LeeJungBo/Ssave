package com.jung.Ssave.kakaologin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.kakaologin.domain.KakaoUser;
import com.jung.Ssave.kakaologin.service.KakaoLoginService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/kakao")
@Controller
public class KakaoLoginController {
	
	private KakaoLoginService kakaoLoginService;
	
	public KakaoLoginController(KakaoLoginService kakaoLoginService) {
		this.kakaoLoginService = kakaoLoginService;
	}
	
	@GetMapping("/connect")
	public String kakaoConnect() {
		
		return kakaoLoginService.kakaoConnect();
		
	}
	
	@GetMapping("/callback")
	public String kakaoLogin(
			@RequestParam("code") String code
			, HttpSession session
			, Model model) throws Exception {
		
		String access_Token = kakaoLoginService.getToken(code);
		System.out.println("access_token : " + access_Token);
		
		// 사용자 정보 가져오기
	    KakaoUser kakaoUser = kakaoLoginService.addUserInfo(access_Token);

	    model.addAttribute("kakaoUser", kakaoUser);
	    
	    return "kakaoUser";
	}
	

}
