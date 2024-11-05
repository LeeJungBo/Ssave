package com.jung.Ssave.kakaologin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.kakaologin.domain.KakaoUser;
import com.jung.Ssave.kakaologin.service.KakaoLoginService;
import com.jung.Ssave.ssave.domain.AladdinItemResponse;
import com.jung.Ssave.ssave.service.AladdinItemService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/kakao")
@Controller
public class KakaoLoginController {
	
	private KakaoLoginService kakaoLoginService;
	private AladdinItemService aladinBookService;
	
	public KakaoLoginController(KakaoLoginService kakaoLoginService, AladdinItemService aladinBookService) {
		this.kakaoLoginService = kakaoLoginService;
		this.aladinBookService = aladinBookService;
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
		
		// 세션에서 Access Token을 먼저 확인
		String access_Token = (String) session.getAttribute("access_Token");
		
		if(access_Token == null) {
			
			access_Token = kakaoLoginService.getToken(code);
			System.out.println("access_token : " + access_Token);
			session.setAttribute("access_Token", access_Token);
			
		}else {
			 
			System.out.println("Existing access_token : " + access_Token);
		
		}
		
		// 사용자 정보 가져오기
	    KakaoUser kakaoUser = kakaoLoginService.getUserInfo(access_Token);

	   if(kakaoUser != null) {
		   
		   session.setAttribute("kakaoUserId", kakaoUser.getId());
		   session.setAttribute("kakaoNickName", kakaoUser.getNickName());
		   
		   AladdinItemResponse aladdinItemResponse = aladinBookService.connectAladin();
		
		   model.addAttribute("aladdinItemResponseList",aladdinItemResponse.getItem());
	   
		   return "/ssave/ssaveList";
	   
	   }else {
		   
		   return "redirect:/error" ;
	   
	   }
	   
	    
	}
	

}
