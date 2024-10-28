package com.jung.Ssave.bookmark.service;

import org.springframework.stereotype.Service;

import com.jung.Ssave.bookmark.domain.BookMark;
import com.jung.Ssave.bookmark.repository.BookMarkRepository;

@Service
public class BookMarkService {

	private BookMarkRepository bookMarkRepository;
	
	public BookMarkService(BookMarkRepository bookMarkRepository) {
		
		this.bookMarkRepository = bookMarkRepository;
	
	}
	
	public BookMark addBookMark(int userId
								, long itemId) {
		
		BookMark bookMark = BookMark.builder()
									.userId(userId)
									.itemId(itemId)
									.build();
		
		BookMark result = bookMarkRepository.save(bookMark);
		
		return result;
		
	}
}
