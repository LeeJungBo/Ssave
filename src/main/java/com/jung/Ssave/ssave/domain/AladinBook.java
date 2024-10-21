package com.jung.Ssave.ssave.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class AladinBook {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String author; // 저자
    
	private String category; // 그 책의 카테고리
    
	private String description; // 책 설명

    private String publisher; // 출판사

    private String pubDate; // 출판 날짜

    private String cover; // 표지 사진
    
    private int pricesales; // 판매가
    
    private int pricestandard; // 정가
    

	
}
