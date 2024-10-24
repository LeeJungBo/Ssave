package com.jung.Ssave.ssave.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jung.Ssave.ssave.domain.AladdinItemResponse;

@Service
public class AladdinItemService {

	private final String API_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
	
	
	public AladdinItemResponse connectAladin(String ttbkey, String queryType, int maxResults, int start,  String searchTarget, String output) {
		
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
	
}
