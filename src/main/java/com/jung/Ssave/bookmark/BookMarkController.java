package com.jung.Ssave.bookmark;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jung.Ssave.bookmark.dto.MyBookMarkView;
import com.jung.Ssave.bookmark.service.BookMarkService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/bookmarks")
@Controller
public class BookMarkController {

	private BookMarkService bookMarkService;
	
	public BookMarkController(BookMarkService bookMarkService) {
		
		this.bookMarkService = bookMarkService;
		
	}
	
	@GetMapping("/mybookmarkview")
	public String myBookMarkView(HttpSession session
								, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<MyBookMarkView> myBookMarkList = bookMarkService.myBookMark(userId);
		
		model.addAttribute("myBookMarkList", myBookMarkList);
		
		return "bookmark/myBookMark";
	}
	
}
