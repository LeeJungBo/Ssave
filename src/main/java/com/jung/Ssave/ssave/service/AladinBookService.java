package com.jung.Ssave.ssave.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jung.Ssave.ssave.domain.AladinBook;
import com.jung.Ssave.ssave.dto.AladinBookResponse;
import com.jung.Ssave.ssave.dto.AladinBookView;
import com.jung.Ssave.ssave.repository.AladinBookRepository;

@Service
public class AladinBookService {


	private AladinBookRepository aladinBookRepository;
	
	private final String API_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
	
	public AladinBookService(AladinBookRepository aladinBookRepository) {
		
		this.aladinBookRepository = aladinBookRepository;
		
	}
	
	public void connectAladin(String ttbkey, String queryType, int maxResults, int start,  String searchTarget, String output) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = API_URL + "?ttbkey=" + ttbkey
							 + "&QueryType=" + queryType
							 + "&MaxResults=" + maxResults
							 + "&start=" + start
							 + "&SearchTarget=" + searchTarget
							 + "&output=" + output
							 + "&Version=20131101";
		
		AladinBookResponse response = restTemplate.getForObject(url, AladinBookResponse.class);
	
		if(response != null && response.getAladinBookList() != null) {
			
			for(AladinBookView book : response.getAladinBookList()) {
				
				AladinBook aladinBook = new AladinBook(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getDescription(), book.getPublisher(), book.getPubDate(), book.getCover(), book.getPricesales(), book.getPricestandard());
			
				aladinBookRepository.save(aladinBook);
			}
		}
	}
	
	
	
	
}
