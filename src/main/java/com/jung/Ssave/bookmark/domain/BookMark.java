package com.jung.Ssave.bookmark.domain;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="bookmark")
@Builder
@Entity
public class BookMark {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	private String isbn13;
	
	private String title;
	
	private String cover;
	
	@Column(name="priceSales")
	private int priceSales;
	
	private String link;
	
	private String author;
	
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
}
