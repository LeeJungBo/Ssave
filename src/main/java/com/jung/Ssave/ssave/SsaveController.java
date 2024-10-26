package com.jung.Ssave.ssave;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.ssave.domain.AladdinItemDetailResponse;
import com.jung.Ssave.ssave.domain.AladdinItemResponse;
import com.jung.Ssave.ssave.service.AladdinItemService;


@Controller
@RequestMapping("/ssave")
public class SsaveController {

	private AladdinItemService aladinBookService;
	
	@Value("${aladin.ttbkey}")
	private String ttbkey;
	
	public SsaveController(AladdinItemService aladinBookService) {
		
		this.aladinBookService = aladinBookService;
	}
	

	@GetMapping("/listView")
	public String connectAladin(Model model) {
        
		
		String queryType = "ItemNewAll";
		int maxResults = 50;
		int start = 1;
		String searchTarget = "Book";
		String output = "JS";
		
		AladdinItemResponse aladdinItemResponse = aladinBookService.connectAladin(ttbkey, queryType, maxResults, start, searchTarget, output);
		
		model.addAttribute("aladdinItemResponseList",aladdinItemResponse.getItem()); // 아예 직접적으로 리스를 내가 get으로 끌고와서 해보자
																				     // 맞네 자꾸 기본장착된 tostring()메소드가 호출되게 되어서 AladdinItemResponse 객체 자체를 뷰로 전달되었기 때문이다.
		return "ssave/ssaveList";
        
	}
	
	@GetMapping("/listView-search")
	public String searchAladin( @RequestParam("keyword") String keyword
							   , Model model) {
		
		String Query = keyword;
		String queryType = "Keyword";
		int maxResults = 10;
		int start = 1;
		String searchTarget = "Book";
		String output = "JS";
		
		AladdinItemResponse aladdinItemResponse = aladinBookService.searchAladin(ttbkey, Query, queryType, maxResults, start, searchTarget, output);
		model.addAttribute("itemList", aladdinItemResponse.getItem());
		
		return "ssave/ssaveSearchList";
	}
	
	@GetMapping("/detail")
	public String detailAladin(@RequestParam("itemId") String itemId) {
		
		String itemIdType = "ISBN13";
		String ItemId = itemId;
		String output = "JS";
		String OptResult = "ebookList,usedList,reviewList";
		
		AladdinItemDetailResponse aladdinItemDetailResponse = aladinBookService.detailAladin(ttbkey, itemIdType, ItemId, output, OptResult);
		
		return "ssave/ssaveSearchList";
	}
	
	
	
	
}
