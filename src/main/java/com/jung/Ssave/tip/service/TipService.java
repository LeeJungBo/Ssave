package com.jung.Ssave.tip.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jung.Ssave.tip.domain.Tip;
import com.jung.Ssave.tip.repository.TipRepository;

@Service
public class TipService {

	private TipRepository tipRepository;
	
	public TipService(TipRepository tipRepository) {
		this.tipRepository = tipRepository;
	}
	
	public Tip addTip(int userId, int productId, String title, String contents){
		
		Tip tip = Tip.builder()
				     .userId(userId)
				     .productId(productId)
				     .title(title)
				     .contents(contents)
				     .build();
		
		Tip result = tipRepository.save(tip);
		
		return result;
				     
	}
	
	public Tip getTip(int id) {
		
		Optional<Tip> optionalTip = tipRepository.findById(id);
		
		Tip tip = optionalTip.orElse(null);
		
		return tip;
		
	}
	
}
