package com.jung.Ssave.tip;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Ssave.tip.domain.Tip;
import com.jung.Ssave.tip.dto.TipListView;
import com.jung.Ssave.tip.service.TipService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tip")
public class TipController {
	
	private TipService tipService;
	
	public TipController(TipService tipService) {
		
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
	
	
	
	@GetMapping("/detail-view")
	public String tipDetail(
			@RequestParam("id") int id
			, Model model) {
		
		Tip tip = tipService.getTip(id);
		
		model.addAttribute("tip", tip);
		
		return "tip/tipDetail";
		
	}
	
	
	
	
}
