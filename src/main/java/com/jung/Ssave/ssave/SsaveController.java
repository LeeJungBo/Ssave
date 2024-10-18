package com.jung.Ssave.ssave;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ssave")
public class SsaveController {

	@GetMapping("/list-view")
	public String ssaveList() {
	
		return "ssave/ssaveList";
		
	}
	
	
}
