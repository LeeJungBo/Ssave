package com.jung.Ssave.ssave.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jung.Ssave.bookmark.service.BookMarkService;
import com.jung.Ssave.ssave.domain.AladdinItemDetailResponse;
import com.jung.Ssave.ssave.domain.AladdinItemResponse;


@Service
public class AladdinItemService {

	private final String API_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
	private final String API_URLS = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";
	private final String API_URLD = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";
	
	@Value("${aladin.ttbkey}")
	private String ttbkey;
	
	
	
	public AladdinItemResponse connectAladin() {
		
		String queryType = "Bestseller";
		int maxResults = 50;
		int start = 1;
		String searchTarget = "Book";
		String output = "JS";
		
		RestTemplate restTemplate = new RestTemplate();
		String url = API_URL + "?ttbkey=" + ttbkey
							 + "&QueryType=" + queryType
							 + "&MaxResults=" + maxResults	
							 + "&start=" + start
							 + "&SearchTarget=" + searchTarget
							 + "&output=" + output
							 + "&Version=20131101";
		
		AladdinItemResponse response = restTemplate.getForObject(url, AladdinItemResponse.class);
		// AladinBookResponse.class 이 클래스를 그 알라딘의 응답 구조와 똑같이 만들어주자 우리 그 dto만들어줄때 했다.
		
		return response;
	
	}
	
	
	
	
	public AladdinItemResponse searchAladin(String Query){
		
		String queryType = "Keyword";
		int maxResults = 10;
		int start = 1;
		String searchTarget = "Book";
		String output = "JS";
		
		RestTemplate restTemplate = new RestTemplate();
		String url = API_URLS + "?ttbkey=" + ttbkey
							  + "&Query=" + Query
							  + "&QueryType=" + queryType
							  + "&MaxResults=" + maxResults	
							  + "&start=" + start
							  + "&SearchTarget=" + searchTarget
							  + "&output=" + output
							  + "&Version=20131101";
		System.out.println("API URL: " + url);
		
		try {
		    
			AladdinItemResponse response = restTemplate.getForObject(url, AladdinItemResponse.class);
		    return response;
		
		} catch (Exception e) {
		    
			System.out.println("API 호출 에러: " + e.getMessage());
		    e.printStackTrace();
		    return null;
		
		}
		
		
	}
	
	public AladdinItemDetailResponse detailAladin(String isbn13, int userId){

				String itemIdType = "ISBN13";
				String output = "JS";
				String OptResult = "ebookList,usedList,reviewList";
				String Cover = "Big";
		
				RestTemplate restTemplate = new RestTemplate();
				String url = API_URLD + "?ttbkey=" + ttbkey
									  + "&itemIdType=" + itemIdType
									  + "&ItemId=" + isbn13
									  + "&output=" + output	
									  + "&Version=20131101"
									  + "&OptResult=" + OptResult
									  + "&Cover=" + Cover;
								
				
				try {
					
					AladdinItemDetailResponse aladdinItemDetailResponse = restTemplate.getForObject(url, AladdinItemDetailResponse.class);
					
					return aladdinItemDetailResponse;
				
				} catch (Exception e) {
						System.out.println("API 호출 에러: " + e.getMessage());
						e.printStackTrace();
						return null;
				}

	}
	
	
	
}
