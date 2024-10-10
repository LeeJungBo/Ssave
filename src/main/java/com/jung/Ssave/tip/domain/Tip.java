package com.jung.Ssave.tip.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@Table(name="tip")
@Entity
public class Tip {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="userId")
	private int userId;
	@Column(name="productId")
	private int productId;
	private String title;
	private String contents;
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	
	
	
	
	
	
	
}
