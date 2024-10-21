package com.jung.Ssave.ssave.dto;

import java.util.List;

public class AladinBookResponse {

	private List<AladinBookView> aladinBookList;
	
	public List<AladinBookView> getAladinBookList(){
		
		return aladinBookList;
	
	}
	
	public void setAladinBookList(List<AladinBookView> aladinBookList) {
		
		this.aladinBookList = aladinBookList;
		
	}
	
}
