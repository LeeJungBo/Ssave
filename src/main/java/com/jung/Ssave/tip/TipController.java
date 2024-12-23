package com.jung.Ssave.tip;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.ssave.domain.Item;
import com.jung.Ssave.ssave.service.AladdinItemService;
import com.jung.Ssave.tip.domain.Tip;
import com.jung.Ssave.tip.dto.TipListView;
import com.jung.Ssave.tip.service.TipService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tip")
public class TipController {
	
	private TipService tipService;
	private AladdinItemService aladdinItemService;
	
	
	public TipController(TipService tipService, AladdinItemService aladdinItemService) {
		
		this.aladdinItemService = aladdinItemService;
		this.tipService = tipService;
	}
	
	
	@GetMapping("/list-view")
	public String tipList(
			Model model) {
		
		
		List<TipListView> tipListView = tipService.getTipList();
		
		model.addAttribute("tipListView", tipListView);
		
		return "tip/tipList";
		
	}
	
	
	@GetMapping("/write-view")
	public String tipWrite() {
		
		return "tip/tipWrite";
		
	}
	
	@GetMapping("/write-item-view")
	public String itemTipWrite(
			@RequestParam("isbn13") String isbn13
			, Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
	//	Item item = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0);
		
		Item item = aladdinItemService.getItem(isbn13, userId); 
		// 최고로 좋은건 응답값과 받아온 값의 dto를 구분해줘서 하는거고 적어도 이정도는 되어야함 이렇게 바로 받아올수 있게끔
		
		model.addAttribute("item", item);
		
		return "tip/tipWrite";
		
	}
	
	
	@GetMapping("/detail-view")
	public String tipDetail(
			@RequestParam("id") int id
			, Model model) {
		
		Tip tip = tipService.getTip(id);
		
		model.addAttribute("tip", tip);
		
		return "tip/tipDetail";
		
	}
	
	@GetMapping("/update-view")
	public String tipUpdate(
			Model model
			, @RequestParam("id") int id) {
		
		Tip tip = tipService.getTip(id);
		
		model.addAttribute("tip", tip);
		
		return "tip/tipUpdate";
		
	}
	
	
	
	
}
