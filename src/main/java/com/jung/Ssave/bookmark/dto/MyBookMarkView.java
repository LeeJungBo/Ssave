package com.jung.Ssave.bookmark.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyBookMarkView {

	private String title;
	
	private String cover;
	
	private int priceSales;
	
	private String link;
	
	private String author;
	
}
