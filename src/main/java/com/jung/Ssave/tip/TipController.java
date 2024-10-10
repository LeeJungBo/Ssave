package com.jung.Ssave.tip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tip")
public class TipController {
	
	
	
	@GetMapping("/list-view")
	public String tipList() {
		
		return "tip/tipList";
		
	}
	
	
	
	@GetMapping("/write-view")
	public String tipWrite() {
		
		return "tip/tipWrite";
		
	}
	
	
	
	@GetMapping("/detail-view")
	public String tipDetail() {
		
		return "tip/tipDetail";
		
	}
	
	
	
	
}
