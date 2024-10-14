package com.jung.Ssave.tip;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Ssave.tip.domain.Tip;
import com.jung.Ssave.tip.service.TipService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/tip")
public class TipRestController {
	
	private TipService tipService;
	
	public TipRestController(TipService tipService) {
		this.tipService = tipService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createTip(
			@RequestParam("productId") int productId
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session){
			
			int userId = (Integer)session.getAttribute("userId");
		
			Tip tip = tipService.addTip(userId, productId, title, contents);
			
			Map<String, String> resultMap = new HashMap<>();
			if(tip != null) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		
	}
	
	
	
}
