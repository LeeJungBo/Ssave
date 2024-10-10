package com.jung.Ssave.tip.service;

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
				     .contents(contents)
				     .build();
		
		Tip result = tipRepository.save(tip);
		
		return result;
				     
			
	}
	
}
