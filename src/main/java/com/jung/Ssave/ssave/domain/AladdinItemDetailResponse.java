package com.jung.Ssave.ssave.domain;

import java.util.List;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AladdinItemDetailResponse {
	
	 
	 private List<Item> item;
	 
	 
	 @NoArgsConstructor
	 @AllArgsConstructor
	 @Getter
	 @Setter
	 public static class SubInfo {
		 
		 private int itemPage;
		 
	 
	 }
	 
	 
	 
		 
	 
	 
}
