package com.jung.Ssave.ssave.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AladinBookView {

	@Id
	private int id;
	
	private String title;
	
	private String author;
	
	private String category;
	
	private String description;
	
	private String publisher;
	
	private String pubDate;
	
	private String cover;
	
	private int pricesales;
	
	private int pricestandard;
	
}
