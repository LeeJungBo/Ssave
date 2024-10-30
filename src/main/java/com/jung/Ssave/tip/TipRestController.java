package com.jung.Ssave.tip;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			 @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value = "cover", required = false) String cover
			, @RequestParam(value = "link", required = false) String link
			, HttpSession session){
			
			int userId = (Integer)session.getAttribute("userId");
		
			Tip tip = tipService.addTip(userId, title, contents, cover, link);
			
			Map<String, String> resultMap = new HashMap<>();
			if(tip != null) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		
	}
	
	
	@PutMapping("/update")
	public Map<String, String> updateTip(
			@RequestParam("id") int id
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Tip tip = tipService.updateTip(id, userId, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		if(tip != null) {
			
			resultMap.put("result", "success");
			
		}else {
			
			resultMap.put("result", "fail");
			
		}
		
		return resultMap;
	}
	
	
	
	
	
}
