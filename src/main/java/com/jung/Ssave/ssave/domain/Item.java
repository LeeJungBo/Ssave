package com.jung.Ssave.ssave.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

	private String title;
	
	private String author;
	
	private String description;
	
	private String publisher;
	
	private String pubDate;
	
	private String cover;
	
	private int pricesales;
	
	private int pricestandard;

	
}
