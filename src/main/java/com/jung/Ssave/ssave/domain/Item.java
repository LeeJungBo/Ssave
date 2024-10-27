package com.jung.Ssave.ssave.domain;

import com.jung.Ssave.ssave.domain.AladdinItemDetailResponse.SubInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

	private long itemId;
	
	private long isbn13;
	
	private String link;
	
	private String title;
	
	private String author;
	
	private String description;
	
	private String publisher;
	
	private String pubDate;
	
	private String cover;
	
	private int priceSales; // 다른 사이트에 있는 변수값과 이름 정확히 매칭해줄것
	
	private int priceStandard;
	
	private SubInfo subInfo;

	
}
