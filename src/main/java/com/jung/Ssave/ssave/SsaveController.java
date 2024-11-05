package com.jung.Ssave.ssave;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.bookmark.service.BookMarkService;
import com.jung.Ssave.ssave.domain.AladdinItemDetailResponse;
import com.jung.Ssave.ssave.domain.AladdinItemDetailResponse.SubInfo;
import com.jung.Ssave.ssave.domain.AladdinItemResponse;
import com.jung.Ssave.ssave.domain.Item;
import com.jung.Ssave.ssave.service.AladdinItemService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ssave")
public class SsaveController {

	private AladdinItemService aladinBookService;
	private BookMarkService bookMarkService;
	
	
	public SsaveController(AladdinItemService aladinBookService, BookMarkService bookMarkService) {
		
		this.aladinBookService = aladinBookService;
		this.bookMarkService = bookMarkService;
		
	}
	

	@GetMapping("/listView")
	public String connectAladin(Model model, HttpSession session) {
        
		
		
		AladdinItemResponse aladdinItemResponse = aladinBookService.connectAladin();
		
		model.addAttribute("aladdinItemResponseList",aladdinItemResponse.getItem()); // 아예 직접적으로 리스를 내가 get으로 끌고와서 해보자
																				     // 맞네 자꾸 기본장착된 tostring()메소드가 호출되게 되어서 AladdinItemResponse 객체 자체를 뷰로 전달되었기 때문이다.
		return "ssave/ssaveList";
        
	}
	
	@GetMapping("/listView-search")
	public String searchAladin( @RequestParam("keyword") String keyword
							   , Model model) {
		
		String Query = keyword;
		
		AladdinItemResponse aladdinItemResponse = aladinBookService.searchAladin(Query);
		model.addAttribute("itemList", aladdinItemResponse.getItem());
		
		return "ssave/ssaveSearchList";
	
	}
	
	@GetMapping("/detail")
	public String detailAladin(@RequestParam("isbn13") String isbn13
								, Model model
								, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		AladdinItemDetailResponse aladdinItemDetailResponse = aladinBookService.detailAladin(isbn13, userId);
		Item item = aladinBookService.getItem(isbn13, userId);
		boolean isBookMark = bookMarkService.isBookMarkByUserIdAndItemId(userId, isbn13);
		SubInfo subInfo = aladinBookService.getSubInfo(isbn13, userId);
		
		model.addAttribute("isBookMark", isBookMark);
		model.addAttribute("aladdinItemDetailResponse", aladdinItemDetailResponse);
		model.addAttribute("item", item);
		model.addAttribute("subInfo", subInfo);
		
		return "ssave/ssaveDetail";
		
	}
	
}
