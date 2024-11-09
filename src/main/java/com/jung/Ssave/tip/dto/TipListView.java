package com.jung.Ssave.tip.dto;



import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipListView {

	private int tipId;
	
	private String title;
	private String loginId; 
	private String name;
	
}
