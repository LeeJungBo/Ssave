package com.jung.Ssave.bookmark;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Ssave.bookmark.domain.BookMark;
import com.jung.Ssave.bookmark.service.BookMarkService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/bookmark")
public class BookMarkRestController {

	private BookMarkService bookMarkService;
	
	public BookMarkRestController(BookMarkService bookMarkService) {
		
		this.bookMarkService = bookMarkService;
		
	}
	
	@PostMapping("/create")
	public Map<String, String> createBookMark(@RequestParam("itemId") long itemId
											, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		BookMark bookMark = bookMarkService.addBookMark(userId, itemId);
		
		Map<String, String> resultMap = new HashMap<>();
		if(bookMark != null) {
			
			resultMap.put("result", "success");
			
		}else {
			
			resultMap.put("result", "fail");
		
		}
		
		return resultMap;
		
		
	}
	
}
