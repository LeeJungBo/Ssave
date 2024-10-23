package com.jung.Ssave.ssave;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Ssave.ssave.domain.AladdinItemResponse;
import com.jung.Ssave.ssave.service.AladinBookService;

@RestController
@RequestMapping("/aladin")
public class AladinBookRestController {

	private AladinBookService aladinBookService;
	
	@Value("${aladin.ttbkey}")
	private String ttbkey;
	
	public AladinBookRestController(AladinBookService aladinBookService) {
		
		this.aladinBookService = aladinBookService;
	}
	

	@GetMapping("/listView")
	public AladdinItemResponse connectAladin() {
        
		
		String queryType = "ItemNewAll";
		int maxResults = 100;
		int start = 1;
		String searchTarget = "Book";
		String output = "JS";
		
		return aladinBookService.connectAladin(ttbkey, queryType, maxResults, start, searchTarget, output);
		
		
        
	}
	
	
}
