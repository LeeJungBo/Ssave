package com.jung.Ssave.tip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jung.Ssave.tip.domain.Tip;
import com.jung.Ssave.tip.dto.TipListView;
import com.jung.Ssave.tip.repository.TipRepository;
import com.jung.Ssave.user.domain.User;
import com.jung.Ssave.user.service.UserService;

@Service
public class TipService {

	private TipRepository tipRepository;
	private UserService userService;
	
	public TipService(TipRepository tipRepository, UserService userService) {
		this.tipRepository = tipRepository;
		this.userService = userService;
	}
	
	public Tip addTip(int userId, String title, String contents, String cover, String link){
		
		Tip tip = Tip.builder()
				     .userId(userId)
				     .title(title)
				     .contents(contents)
				     .cover(cover)
				     .link(link)
				     .build();
		
		Tip result = tipRepository.save(tip);
		
		return result;
				     
	}
	
	public Tip getTip(int id) {
		
		Optional<Tip> optionalTip = tipRepository.findById(id);
		
		Tip tip = optionalTip.orElse(null);
		
		return tip;
		
	}
	
	public Tip updateTip(int id, int userId, String title, String contents) {
		
		Optional<Tip> optionalTip = tipRepository.findByUserIdAndId(userId, id);
		Tip tip = optionalTip.orElse(null);
		
		if(tip != null) {
			
			Tip updateTip = tip.toBuilder()
							   .title(title)
							   .contents(contents)
							   .build();
			
			return tipRepository.save(updateTip);
			
		}else {
			
			return null;
		}
		
	}
	
	public List<TipListView> getTipList(){
		
		List<Tip> tipList =  tipRepository.findAll();
		
		List<TipListView> tipListViewList = new ArrayList<>();
		for(Tip tip:tipList) {
			int userId = tip.getUserId();
			User user = userService.getUserById(userId);
			// 이게 매칭이 되어야 리스트에도 한칸에 다들어갈수있음 따라서 이렇게 따로 리스트로만들면 안되고 한 반복문안에서 추출해줘서 나올수 있게 해준다.
			
			TipListView tipListView = TipListView.builder()
											     .tipId(tip.getId())
									             .title(tip.getTitle())
									             .loginId(user.getLoginId())
									             .name(user.getName())
									             .build();
			
			tipListViewList.add(tipListView);
		// 이렇게 넣어줘야 리스트에 타이틀과 loginId가 같이 들어갈수 있음
		}
		
		return tipListViewList;
		
	}
	
	public boolean deleteTip(int id){
		
		Optional<Tip> optionalTip = tipRepository.findById(id);
		Tip tip = optionalTip.orElse(null);
		
		if(tip != null) {
			
			tipRepository.delete(tip);
			return true;
		
		}else {
			
			return false;
			
		}
		
		
		
	}
	
}
