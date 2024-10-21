package com.jung.Ssave.ssave;

import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.ssave.service.AladinBookService;

public class AladinBookRestController {

	private AladinBookService aladinBookService;
	
	public AladinBookRestController(AladinBookService aladinBookService) {
		
		this.aladinBookService = aladinBookService;
		
	}
	


	public String connectAladin(@RequestParam String ttbkey, @RequestParam String queryType, @RequestParam int maxResults, @RequestParam int start, @RequestParam String searchTarget, @RequestParam String output) {
        
		aladinBookService.connectAladin(ttbkey, queryType, maxResults, start, searchTarget, output);
        
		return "Items fetched and saved successfully";
    }
	
	
}
